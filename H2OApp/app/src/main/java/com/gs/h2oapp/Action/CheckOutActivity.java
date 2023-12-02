package com.gs.h2oapp.Action;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
//import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
//import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
//import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.gs.h2oapp.Adapter.ProductAdapter;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Dao.ProductDao;
import com.gs.h2oapp.DataBase.TaComSedeDatabase;
import com.gs.h2oapp.Entity.CompanyPaymentTypes;
import com.gs.h2oapp.Entity.Coupon;
import com.gs.h2oapp.Entity.ItemCart;
import com.gs.h2oapp.Entity.Order;
//import com.gs.h2oapp.Entity.Product;
import com.gs.h2oapp.Entity.ProductOrder;
import com.gs.h2oapp.Entity.User;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Repository.OrderRepository;
import com.gs.h2oapp.Repository.UserRepository;
import com.gs.h2oapp.Utils.Configuration;
import com.gs.h2oapp.Utils.InitializerApi;
import com.gs.h2oapp.Utils.Utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutActivity extends AppCompatActivity implements View.OnFocusChangeListener{

    private Toolbar toolbar;
    private TextInputEditText txtName, txtFone, txtNumber, txtComp, txtDicas, txtCupomDesc;
    private TextView lblCupomDesc, txtEntrega,txtTotal,txtRua, txtBairroCity, txtPaymentCred,
            txtPaymentDeb, txtPaymentMon,lblTitleDadosCliente, lblUpdtUser;
    private TextInputLayout iptLytName, iptLytFone, iptLytNumber ; //, iptLytComp, iptLytDicas, iptLytCupomDesc;
    private LinearLayout divPagMoney, divPagDeb, divPagCred;
    private ImageButton divBtnPagMoney, divBtnPagDeb, divBtnPagCred;
    private Button btnSignUp;
    private CarrinhoBusiness cartBusiness;
    private ProgressBar spinner;
    private String formaPag = "";
    private String cupomDesconto;
    private InputMethodManager keyboard;
    private LinearLayout divCupomDesconto;
    private LinearLayout divTitleFormaPgto;
    private LinearLayout divDadosClient;
    private LinearLayout divDadosEntrega;
    private LinearLayout divDadosDicasEntrega;
   // private LinearLayout divBtnConfirmar;
    private BigDecimal tot = BigDecimal.ZERO;
    private String troco = "0,00";
    private UserRepository userRepository;
   // private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        toolbar = findViewById(R.id.toolbar_check_out);
        setSupportActionBar(toolbar);
        TextView toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
       // toolBarTitle.setText("Confirmar Pedido");
        toolBarTitle.setText(getString(R.string.confirm_order));
        spinner= findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        if(null == cartBusiness){
            cartBusiness = new CarrinhoBusinessImp();
        }
        if(null == userRepository){
            userRepository = new UserRepository(getApplicationContext());
        }
        initViews();
        populateViews();
        loadPaymentForms();
        setPayMoneyNoSelected();
        setPayCreditNoSelected();
        setPayDebitNoSelected();
        //scrollFields();
        updateUserData();

        lblCupomDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    spinner.setVisibility(View.VISIBLE);
            //    submitForm();
                showDlgCoupon();
               // System.out.println("CLIQUEI NO LABEL");
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                submitForm();
            }
        });
        //requestFocus(txtName);
        spinner.setVisibility(View.GONE);
    }

    private void updateUserData(){
        //PEGANDO OS DADOS DO USUÁRIO PREVIAMENTE CADASTRADOS
        getUser();
        if (null != cartBusiness.getUser()){
            lblTitleDadosCliente = findViewById(R.id.lblTitleDadosCliente);
            lblUpdtUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDlgUpdtUser();
                }
            });
            if (lblTitleDadosCliente != null) {
                lblTitleDadosCliente.setText("Seus Dados:");
            }
            if (txtName != null) {
                txtName.setText(cartBusiness.getUser().getName());
                iptLytName.setEnabled(false);
            }
            if (txtFone != null) {
                txtFone.setText(cartBusiness.getUser().getFone());
                txtFone.setEnabled(false);
            }
        } else{
            txtName.setEnabled(true);
            txtFone.setEnabled(true);
            lblUpdtUser.setVisibility(View.INVISIBLE);
            lblTitleDadosCliente.setText(R.string.pre_dados);
        }
    }
    private void initViews(){
        iptLytName =  findViewById(R.id.input_layout_name);
        iptLytFone = findViewById(R.id.input_layout_fone);
        iptLytNumber =  findViewById(R.id.input_layout_number);
       // iptLytComp =  findViewById(R.id.input_layout_complemento);
      //  iptLytCupomDesc =  findViewById(R.id.input_layout_cupom_desc);
        //iptLytDicas =  findViewById(R.id.input_layout_dicas);
        // iptLytDicas =  findViewById(R.id.input_layout_adress);
        // iptLytCupomDesc =  findViewById(R.id.input_layout_bairro_city);
        txtTotal = findViewById(R.id.tv_total);
        lblCupomDesc = findViewById(R.id.tv_cupom_desc);
        lblUpdtUser = findViewById(R.id.tv_updt_user);
        lblTitleDadosCliente = findViewById(R.id.lblTitleDadosCliente);
        txtEntrega = findViewById(R.id.tv_entrega);
        txtName =  findViewById(R.id.input_name);
        txtFone =  findViewById(R.id.input_fone);
        txtNumber = findViewById(R.id.input_number);
        txtComp = findViewById(R.id.input_complemento);
      //  txtCupomDesc = findViewById(R.id.input_cupom_desc);
        txtRua = findViewById(R.id.txtRua);
        txtBairroCity = findViewById(R.id.txtBairroCity);
        txtDicas =  findViewById(R.id.input_dicas);
        //txtDicas = findViewById(R.id.input_adress);
        //txtBairroCity = findViewById(R.id.input_bairro_city);
        txtPaymentCred = findViewById(R.id.lblPagCredito);
        txtPaymentDeb = findViewById(R.id.lblPagDebito);
        txtPaymentMon = findViewById(R.id.lblPagMoney);
        divPagCred = findViewById(R.id.div_pag_credito);
        divBtnPagCred = findViewById(R.id.btnPagCredito);
        divPagDeb = findViewById(R.id.div_pag_debito);
        divBtnPagDeb = findViewById(R.id.btnPagDebito);
        divPagMoney = findViewById(R.id.div_pag_money);
        divBtnPagMoney = findViewById(R.id.btnPagMoney);
        btnSignUp =  findViewById(R.id.btn_signup);
        keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        divCupomDesconto = findViewById(R.id.divCupomDesconto2);
        divTitleFormaPgto = findViewById(R.id.divPayment);
        divDadosClient = findViewById(R.id.divDadosClient);
        divDadosEntrega = findViewById(R.id.divDadosEntrega);
        divDadosDicasEntrega = findViewById(R.id.divDadosDicasEntrega);
       // divBtnConfirmar = findViewById(R.id.div_btn_order_shopping);
       // txtCupomDesc.requestFocus();
        txtNumber.requestFocus();
       // keyboard.showSoftInput(txtCupomDesc, 0);
        keyboard.showSoftInput(txtNumber, 0);
       // keyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);

        // cartBusiness.setPaymentTypeList(lstCompanies.get(i).getPaymentTypeList());
        if(null != cartBusiness.getPaymentTypeList()){
            for(CompanyPaymentTypes paymentTypes : cartBusiness.getPaymentTypeList()){
                switch (paymentTypes.getIdType()){
                    case 2:
                    case 3:
                        divPagCred.setVisibility(View.VISIBLE);
                        divPagDeb.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        divPagMoney.setVisibility(View.VISIBLE);
                        break;
                    default:
                        divPagCred.setVisibility(View.INVISIBLE);
                        divPagDeb.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        }
        txtName.addTextChangedListener(new MyTextWatcher(txtName));
        txtFone.addTextChangedListener(new MyTextWatcher(txtFone));
        txtNumber.addTextChangedListener(new MyTextWatcher(txtNumber));
        txtName.setOnFocusChangeListener(this);
        txtFone.setOnFocusChangeListener(this);
        txtNumber.setOnFocusChangeListener(this);
        txtComp.setOnFocusChangeListener(this);
        txtDicas.setOnFocusChangeListener(this);

        //txtDicas.setOnFocusChangeListener(this);
       // cartBusiness.setValorDescontoCupom(BigDecimal.ZERO);
    }
    private void getTotalWithDiscount(){
        if(txtTotal != null && !cartBusiness.getValor().equals(BigDecimal.ZERO)){
            tot = cartBusiness.getValor();
            tot = tot.subtract(cartBusiness.getValorDescontoCupom());
            tot = tot.add(cartBusiness.getValorEntrega());
            txtTotal.setText("R$".concat(Utils.formatToCurrency(tot)));
        }
    }
    private void getUser(){
        try {
            List<User> lstUser = userRepository.getAllUser();
            if(null == lstUser || lstUser.isEmpty()){
                cartBusiness.setUser(null);
            } else {
                cartBusiness.setUser(lstUser.get(0));
            }
        }
        catch(Exception ex){
            System.out.println("EXCEPTION--->  " + ex);
        }
    }
    private void populateViews() {
        /*if(txtEntrega != null && cartBusiness.getItens().size() > 0){
            txtEntrega.setText("R$".concat(Utils.formatToCurrency(cartBusiness.getValorEntrega())));
        }*/
        getTotalWithDiscount();
       /* if(txtTotal != null && cartBusiness.getValor() != BigDecimal.ZERO){
            tot = cartBusiness.getValor();
            tot = tot.subtract(cartBusiness.getValorDescontoCupom());
            tot = tot.add(cartBusiness.getValorEntrega());
            txtTotal.setText("R$".concat(Utils.formatToCurrency(tot)));
        }*/
       /*
        //PEGANDO OS DADOS DO USUÁRIO PREVIAMENTE CADASTRADOS
        getUser();
        if (null != cartBusiness.getUser()){
            if (txtName != null) {
                txtName.setText(cartBusiness.getUser().getName());
                txtName.setEnabled(false);
            }
            if (txtFone != null) {
                txtFone.setText(cartBusiness.getUser().getFone());
                txtFone.setEnabled(false);
            }
         } else{
            txtName.setEnabled(true);
            txtFone.setEnabled(true);
            //txtFone.set
        }*/
        if(txtNumber != null){
            txtNumber.setText(cartBusiness.getNumber());
        }
        if(txtRua != null){
            txtRua.setText(cartBusiness.getAdress());
        }
        if(txtComp != null){
            txtComp.setText(cartBusiness.getComplemento());
        }
        if(txtBairroCity != null){
            //Erro aqui
          //  txtBairroCity.setText(cartBusiness.getBairro().concat(", ").concat(cartBusiness.getCity()));
            if(null != cartBusiness.getBairro() && null != cartBusiness.getCity()){
                txtBairroCity.setText(cartBusiness.getBairro().concat(", ").concat(cartBusiness.getCity()));
            } else if(null != cartBusiness.getBairro()){
                txtBairroCity.setText(cartBusiness.getBairro());
                Toast.makeText(getApplicationContext(), "Insira a Cidade de entrega no Endereço!", Toast.LENGTH_LONG).show();
            } else{
                txtBairroCity.setText(cartBusiness.getCity());
                Toast.makeText(getApplicationContext(), "Insira o Bairro de entrega no Endereço!", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void loadPaymentForms(){
        if(null != divPagCred){
            divPagCred.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setPayment(1);
                }
            });
        }
        if(null != divBtnPagCred){
            divBtnPagCred.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setPayment(1);
                }
            });
        }

        if(null != divPagDeb){
            divPagDeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setPayment(2);
                }
            });
        }
        if(null != divBtnPagDeb){
            divBtnPagDeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setPayment(2);
                }
            });
        }
        if(null != divPagMoney){
            divPagMoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setPayment(3);
                }
            });
        }
        if(null != divBtnPagMoney){
            divBtnPagMoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setPayment(3);
                }
            });
        }
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
      // System.out.println("PEGA VEIA-----------");
       /* if (hasFocus) {
            switch (v.getId()){
                case R.id.input_name:
                   jesus(2);
                    break;
                case R.id.input_fone:
                    jesus(3);
                    break;
                case R.id.input_number:
                    jesus(4);
                    break;
                case R.id.input_complemento:
                   jesus(5);
                    break;
                case R.id.input_dicas:
                   jesus(6);
                    break;
            }
        }
        if (!hasFocus){
        }*/
    }
    private void jesus(int id){
      // final NestedScrollView divScrollGeral = findViewById(R.id.scrool_gerais);
        switch (id){
            case 2:
                   /* if (null != divCupomDesconto) {
                       // divCupomDesconto.setTop(-470);
                        divCupomDesconto.setTop(0);
                    }*/
                    if (null != divDadosEntrega) {
                        divDadosEntrega.setTop(-470);
                    }
                    if (null != divDadosClient) {
                        divDadosClient.setTop(218);
                    }
                   /* if (null != divTitleFormaPgto) {
                        divTitleFormaPgto.setTop(468);
                    }*/



                  /*  if (null != divTitleFormaPgto) {
                        divTitleFormaPgto.setTop(35);
                    }
                    if (null != divDadosClient) {
                        divDadosClient.setTop(258);
                    }
                    if (null != divDadosEntrega) {
                        divDadosEntrega.setTop(468);
                    }*/
           //  keyboard.showSoftInput(txtName, 0);
              txtName.requestFocus();
            break;
            case 3:
                    /*if (null != divCupomDesconto) {
                        divCupomDesconto.setTop(-370);
                    }*/

               /* if (null != divDadosEntrega) {
                    divDadosEntrega.setTop(-370);
                }*/

                /*if (null != divDadosEntrega) {
                    divDadosEntrega.setTop(-570);
                }*/
                if (null != divDadosClient) {
                    divDadosClient.setTop(50);
                }
                if (null != divTitleFormaPgto) {
                    divTitleFormaPgto.setTop(458);
                }

                /*if (null != divDadosDicasEntrega) {
                    divDadosDicasEntrega.setTop(520);
                }*/


                    /*  if (null != divTitleFormaPgto) {
                        divTitleFormaPgto.setTop(-370);
                    }
                    if (null != divDadosClient) {
                        divDadosClient.setTop(30);
                    }
                    if (null != divDadosEntrega) {
                        divDadosEntrega.setTop(258);
                    }
                    if (null != divDadosDicasEntrega) {
                        divDadosDicasEntrega.setTop(520);
                    }*/
                keyboard.showSoftInput(txtFone, 0);
                txtFone.requestFocus();
             break;
            case 4 :
               /* if (null != divDadosClient) {
                    divDadosClient.setTop(-370);
                }
                if (null != divDadosEntrega) {
                    divDadosEntrega.setTop(310);
                }
                if (null != divDadosDicasEntrega) {
                    divDadosDicasEntrega.setTop(568);
                }
                if (null != divBtnConfirmar) {
                    divBtnConfirmar.setTop(730);
                }*/
                /*if (null != divDadosClient) {
                    divDadosClient.setTop(-370);
                }
                if (null != divDadosEntrega) {
                    divDadosEntrega.setTop(310);
                }
                if (null != divDadosDicasEntrega) {
                    divDadosDicasEntrega.setTop(568);
                }
                if (null != divBtnConfirmar) {
                    divBtnConfirmar.setTop(730);
                }*/
                txtNumber.requestFocus();
             break;
            case 5 :
                keyboard.showSoftInput(txtComp, 0);
                txtComp.requestFocus();
             break;
            case 6 :
                keyboard.showSoftInput(txtDicas, 0);
                txtDicas.requestFocus();
            break;
        }

    }
    @SuppressLint("ClickableViewAccessibility")
    private void scrollFields(){
        //txtName.
       txtName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //boolean caraio = false;
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                if (null != divCupomDesconto) {
                    divCupomDesconto.setTop(-370);
                }
                if (null != divTitleFormaPgto) {
                    divTitleFormaPgto.setTop(-370);
                }
                if (null != divDadosClient) {
                    divDadosClient.setTop(130);
                }
                if (null != divDadosEntrega) {
                    divDadosEntrega.setTop(368);
                }
                //keyboard.showSoftInput(txtName, 0);
                txtName.requestFocus();
                System.out.println("PEGASUS");
                return true;
                }else {
                    return false;
                }
            }
        });

        txtFone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if (null != divCupomDesconto) {
                        divCupomDesconto.setTop(-370);
                    }
                    // final LinearLayout divFormaPgto = findViewById(R.id.divFormaPgto);
                    if (null != divTitleFormaPgto) {
                        divTitleFormaPgto.setTop(-370);
                    }
                    if (null != divDadosClient) {
                        divDadosClient.setTop(30);
                    }
                    if (null != divDadosEntrega) {
                        divDadosEntrega.setTop(258);
                    }
                    if (null != divDadosDicasEntrega) {
                        divDadosDicasEntrega.setTop(520);
                    }
                    //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    //keyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
                    //keyboard.showSoftInput(txtFone, 0);
                    txtFone.requestFocus();
                    System.out.println("PEGASUS 2");
                    return true;
                }
                else {
                    return false;
                }

            }
        });

        txtNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (null != divCupomDesconto) {
                    divCupomDesconto.setTop(-370);
                }
                if (null != divTitleFormaPgto) {
                    divTitleFormaPgto.setTop(-370);
                }
                if (null != divDadosClient) {
                    divDadosClient.setTop(-370);
                }
                if (null != divDadosEntrega) {
                    divDadosEntrega.setTop(138);
                }
                if (null != divDadosDicasEntrega) {
                    divDadosDicasEntrega.setTop(468);
                }
                //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
               // keyboard.showSoftInput(txtNumber, 0);
                txtNumber.requestFocus();

                return true;
            }
        });
       txtComp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (null != divCupomDesconto) {
                    divCupomDesconto.setTop(-370);
                }
                if (null != divTitleFormaPgto) {
                    divTitleFormaPgto.setTop(-370);
                }
                if (null != divDadosClient) {
                    divDadosClient.setTop(-370);
                }
                if (null != divDadosEntrega) {
                    divDadosEntrega.setTop(138);
                }
                if (null != divDadosDicasEntrega) {
                    divDadosDicasEntrega.setTop(468);
                }
                // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
               // keyboard.showSoftInput(txtComp, 0);
                txtComp.requestFocus();
                return true;
            }
        });
        txtDicas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (null != divCupomDesconto) {
                    divCupomDesconto.setTop(-370);
                }
                if (null != divTitleFormaPgto) {
                    divTitleFormaPgto.setTop(-370);
                }
                if (null != divDadosClient) {
                    divDadosClient.setTop(-370);
                }
                if (null != divDadosEntrega) {
                    divDadosEntrega.setTop(30);
                }
                if (null != divDadosDicasEntrega) {
                    divDadosDicasEntrega.setTop(368);
                }
                //   keyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
              // keyboard.showSoftInput(txtDicas, 0);

                //divDadosDicasEntrega.requestFocus();
               // txtDicas.requestFocusFromTouch();
                txtDicas.requestFocus();
                return true;
            }
        });

    }
    private void setPayment(int n){
        switch (n) {
            case 1:
                formaPag = "C";
                setPayCreditSelected();
                setPayDebitNoSelected();
                setPayMoneyNoSelected();
                //txtName.requestFocus();
                txtDicas.requestFocus();
                break;
            case 2:
                formaPag = "D";
                setPayDebitSelected();
                setPayCreditNoSelected();
                setPayMoneyNoSelected();
               // hiddenDivCupomDesc();
                //txtName.requestFocus();
                txtDicas.requestFocus();
                break;
            case 3:
                formaPag = "M";
                setPayMoneySelected();
                setPayCreditNoSelected();
                setPayDebitNoSelected();
                //hiddenDivCupomDesc();
                break;
        }
    }
    private void setPayCreditSelected(){
        divPagCred.setBackgroundColor(getResources().getColor(R.color.paymentSelected));
        divBtnPagCred.setBackgroundColor(getResources().getColor(R.color.paymentSelected));
        divBtnPagCred.setBackgroundResource(R.drawable.ic_credit_white_48);
        //divBtnPagCred.setS(R.drawable.ic_credit_white_48);
        txtPaymentCred.setTextColor(getResources().getColor(R.color.white));
        hiddenDivCupomDesc();

    }
    private void setPayCreditNoSelected(){
        divPagCred.setBackgroundColor(getResources().getColor(R.color.paymentNoSelected));
        divBtnPagCred.setBackgroundColor(getResources().getColor(R.color.paymentNoSelected));
        divBtnPagCred.setBackgroundResource(R.drawable.ic_credit_blue_48);
        txtPaymentCred.setTextColor(getResources().getColor(R.color.paymentSelected));
        //hiddenDivCupomDesc();
   }
    private void setPayDebitSelected(){
        divPagDeb.setBackgroundColor(getResources().getColor(R.color.paymentSelected));
        divBtnPagDeb.setBackgroundColor(getResources().getColor(R.color.paymentSelected));
        divBtnPagDeb.setBackgroundResource(R.drawable.ic_debit_card_white_48);
        txtPaymentDeb.setTextColor(getResources().getColor(R.color.white));
        hiddenDivCupomDesc();
    }
    private void setPayDebitNoSelected(){
        divPagDeb.setBackgroundColor(getResources().getColor(R.color.paymentNoSelected));
        divBtnPagDeb.setBackgroundColor(getResources().getColor(R.color.paymentNoSelected));
        divBtnPagDeb.setBackgroundResource(R.drawable.ic_debit_card_blue_48);
        txtPaymentDeb.setTextColor(getResources().getColor(R.color.paymentSelected));
        //hiddenDivCupomDesc();
    }
    private void setPayMoneySelected(){
        divPagMoney.setBackgroundColor(getResources().getColor(R.color.paymentSelected));
        divBtnPagMoney.setBackgroundColor(getResources().getColor(R.color.paymentSelected));
        divBtnPagMoney.setBackgroundResource(R.drawable.ic_carteira_white_48);
        txtPaymentMon.setTextColor(getResources().getColor(R.color.white));
        hiddenDivCupomDesc();
        //Pegando as opções de Troco
        try{
            String[] retornoFormatado = Utils.getCedulasTroco(tot.intValue());
            showAlertOptionsTroco(retornoFormatado);
           /* for (int i = 0; i < retornoFormatado.length; i++){
                //retornoFormatado[i];

            }*/

        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Falha ao mudar a opção de pagamento!", Toast.LENGTH_LONG).show();
        }
    }
    private void setPayMoneyNoSelected(){
        divPagMoney.setBackgroundColor(getResources().getColor(R.color.paymentNoSelected));
        divBtnPagMoney.setBackgroundColor(getResources().getColor(R.color.paymentNoSelected));
        divBtnPagMoney.setBackgroundResource(R.drawable.ic_carteira_blue_48);
        txtPaymentMon.setTextColor(getResources().getColor(R.color.paymentSelected));
        //hiddenDivCupomDesc();
    }
    private void hiddenDivCupomDesc(){
       // keyboard.showSoftInput(txtName, 0);
        txtName.requestFocus();
    }
    @SuppressLint("RestrictedApi")
    private void showAlertOptionsTroco(String[] lstFiltersDTO) {
        final LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View viewL = layoutInflater.inflate(R.layout.dialog_options_troco, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LinearLayout divGeralFiltrarCompanies =  viewL.findViewById(R.id.div_geral_troco);
        builder.setView(viewL);
        final AlertDialog litersDialog = builder.create();
        divGeralFiltrarCompanies.removeAllViews();
        int lines = Utils.calculateDivFilters(lstFiltersDTO.length) + 2;
        //Inserir botões na linha até dois itens;
        int lim;
        int ini;
        for (int z = 1; z <= lines; z++) {
            LinearLayout div = (LinearLayout) layoutInflater.inflate(R.layout.div_content_filtros1, null);
            ini = 2 * (z - 1);
            lim = z * 2;
             for (int i = ini; i < lim; i++) {
                if(lstFiltersDTO.length > i) {
                    final AppCompatButton btn1 = (AppCompatButton) layoutInflater.inflate(R.layout.content_troco, null);
                    btn1.setText(lstFiltersDTO[i]);
                    div.addView(btn1);
                    if(troco.equals(lstFiltersDTO[i])){
                        btn1.setTextColor(getResources().getColor(R.color.white));
                        btn1.setBackgroundColor(getResources().getColor(R.color.paymentSelected));
                        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{getResources().getColor(R.color.paymentSelected)});
                        btn1.setSupportBackgroundTintList(csl);
                    }
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            troco = btn1.getText().toString();
                            litersDialog.dismiss();
                            //txtName.requestFocus();
                            txtDicas.requestFocus();
                        }
                    });
                }
            }
            //Add a linha na div geral; lstBrandsFilter.size()
            divGeralFiltrarCompanies.addView(div);
        }
        spinner.setVisibility(View.GONE);
        Button btnSemTroco = viewL.findViewById(R.id.btn__sem_troco);
        if(null != btnSemTroco){
            btnSemTroco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    litersDialog.dismiss();
                }
            });
        }
        litersDialog.show();
    }

    /**
     * Validating form
     */
    private void submitForm() {
       if (!validateName()) {
           spinner.setVisibility(View.GONE);
            return;
        }

        if (!validateFone()) {
            spinner.setVisibility(View.GONE);
            return;
        }

        if (!validateNumber()) {
            spinner.setVisibility(View.GONE);
            return;
        }
        if (!validatePayment()) {
            spinner.setVisibility(View.GONE);
            return;
        }
       // OrderDao Order = TaComSedeDatabase.getInstance(getApplicationContext()).getOrderDao();
        try{
            List<ItemCart> lstItemsCart = cartBusiness.getItens();
                /*o.setAdress(txtDicas.getText().toString().trim());
                o.setBairro(txtBairroCity.getText().toString().trim());
                o.setCity("SBC");
                o.setComplement(txtDicas.getText().toString().trim());
                o.setFone(txtFone.getText().toString().trim());
                o.setName(txtName.getText().toString().trim());
                o.setNameDist("Dist 6");
                o.setNumberHouse(txtNumber.getText().toString().trim());
                o.setUf("SP");
                o.setValorTaxaEntrega(cartBusiness.getValorEntrega().toString());
                o.setValorTotal(Double.toString(cartBusiness.getValor()));*/
            // System.out.println("INSERIDO COM SUCESSO " + idOrder);
            if (lstItemsCart.size() > 0) {
                OrderRepository orderRepository = new OrderRepository(getApplicationContext());
                Order o = new Order();
                o.setIdDist(cartBusiness.getIdCompany());
               // o.setCupomDesconto(txtCupomDesc.getText().toString().trim());
                o.setCupomDesconto(cupomDesconto);
                o.setDicasEntrega(txtDicas.getText().toString().trim());
                o.setFormaPagamento(formaPag);
                o.setTroco(troco);
                o.setAdress(txtRua.getText().toString().trim());
                o.setBairro(cartBusiness.getBairro().trim());
                o.setCity(cartBusiness.getCity());
                o.setComplement(txtComp.getText().toString().trim());
                cartBusiness.setComplemento(txtComp.getText().toString().trim());
                o.setFone(txtFone.getText().toString().trim());
                o.setName(txtName.getText().toString().trim());
                o.setNameDist(cartBusiness.getNameCompany().trim());
                o.setNumberHouse(txtNumber.getText().toString().trim());
                o.setUf("SP");
                o.setValorTaxaEntrega(cartBusiness.getValorEntrega().toString());
                //o.setValorTotal(Double.toString(cartBusiness.getValor()));
                o.setValorDesconto(cartBusiness.getValorDescontoCupom().toString());
                o.setValorSubTotal(cartBusiness.getValor().toString());
                o.setValorTotal(cartBusiness.getValorTotal().toString());
                // o.setValorTotal(cartBusiness.getValor().toString());
                DateFormat date = new SimpleDateFormat("dd MMM yyyy, hh:mm");
                String dateFormatted = date.format(Calendar.getInstance().getTime());
                o.setDataPedido(dateFormatted);

               // long idOrder = orderRepository.insert(o);

              //  List<ProductOrder> lstProductOrder = new ArrayList<>();
                ProductOrder prod;
                o.setLstProduct(new ArrayList<ProductOrder>());
              //  ProductDao productRepository = TaComSedeDatabase.getInstance(getApplicationContext()).getProductDao();
                for(ItemCart itemCart : lstItemsCart){
                    prod = new ProductOrder();
                    // prod.setId(itemCart.getProduct().getIdP());
                    prod.setIdBrand(itemCart.getProduct().getIdB());
                    prod.setIdCompany(itemCart.getProduct().getIdC());
                  //  prod.setIdOrderFK(idOrder);
                    prod.setIdProduct(itemCart.getProduct().getIdP());
                    prod.setLiters(itemCart.getProduct().getLt().toString());
                    prod.setNameProduct(itemCart.getProduct().getNm());
                    prod.setPrecoUnitarioProduto(itemCart.getProduct().getPrecoUnitarioNormalProduto().toString());
                    prod.setQuantidade(itemCart.getQuantidade().toString());
                    o.getLstProduct().add(prod);
                   // lstProductOrder.add(prod);
                }
             //   productRepository.insert(lstProductOrder);

               // o.setLstProduct(lstProductOrder);
               /* Gson gson = new Gson();
                // String adressAsString = gson.toJson(o);
                JsonElement je = gson.toJsonTree(o);
                JsonObject jo = new JsonObject();
                //jo.add("", je);
                jo.add("Order", je);
                System.out.println("INSERIDO COM SUCESSO " + idOrder);
                makeOrderServer(jo);*/
                makeOrderServer(o, orderRepository);
               // makeOrderServer(o, orderRepository, idOrder);

                /*spinner.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Pedido Realizado com Sucesso!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(this, OrderActivity.class);
                startActivity(myIntent);
                */
                //boolean ret =  productRepository.insert(lstProductOrder);
                /*if(ret) {
                    System.out.println("INSERIDO COM SUCESSO " + idOrder);
                    spinner.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Pedido Realizado com Sucesso!", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(this, OrderActivity.class);
                    startActivity(myIntent);
                }*/
            }else{
                spinner.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Seu Carrinho está vazio!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            spinner.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Falha ao realizar o Pedido!".concat(e.toString()), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        /*List<Order>lstOrder = Order.getAllOrders();
        for (Order o2 : lstOrder){
            System.out.println("Teste  " + o2.getIdOrder() + "--->" + o2.getName());
        }
        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
        */

    }

    /*private void makeOrderServer(Order object) {
        Call<Order> call =  new InitializerApi(Configuration.getBaseUrl()).getOrderService().postOrder(object);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Order ord = response.body();
                if(ord != null) {
                    Toast.makeText(getApplicationContext(), "Pedido Realizado com Sucesso!", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getApplicationContext(), OrderActivity.class);
                    startActivity(myIntent);

                }
                else{
                   // Toast.makeText(ProductsActivity.this, "Nenhum Produto Encontrado!", Toast.LENGTH_LONG).show();
                    //lstProd = new ArrayList<>();
                    //adapter = new ProductAdapter(lstProd, ProductsActivity.this);
                    Toast.makeText(getApplicationContext(), "Não foi possível realizar o Pedido!", Toast.LENGTH_LONG).show();


                }
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                spinner.setVisibility(View.GONE);
              //  Log.e("onFaillure chamado ", t.getCause().getMessage());
                Log.e("onFaillure chamado ", t.getMessage());
            }
        });
    }*/

    private void showDlgUpdtUser() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialog_update_user, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(promptView);
        final AlertDialog litersDialog = builder.create();
        //COLOCANDO OS BOTÕES DO CONTENT USER OCULTO
        LinearLayout divBtnsUser = promptView.findViewById(R.id.div_bnt_user_updt);
        divBtnsUser.setVisibility(View.INVISIBLE);
        final TextInputEditText txtNameUpdt, txtFoneUpdt;
        final User u = cartBusiness.getUser();
        txtNameUpdt = promptView.findViewById(R.id.input_name_update_user);
        txtFoneUpdt = promptView.findViewById(R.id.input_fone_update_user);
        txtNameUpdt.setText(u.getName());
        txtFoneUpdt.setText(u.getFone());
        txtNameUpdt.requestFocus();
        keyboard.showSoftInput(txtNameUpdt, 0);
        Button btnEnviar = promptView.findViewById(R.id.btn_dlg_user_updt);
        if(null != btnEnviar){
            btnEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   try{
                       if(null != txtNameUpdt.getText() && null != txtFoneUpdt.getText()){
                           litersDialog.dismiss();
                           spinner.setVisibility(View.VISIBLE);
                           u.setName(txtNameUpdt.getText().toString().trim());
                           u.setFone(txtFoneUpdt.getText().toString().trim());
                           int i = userRepository.update(u);
                           if(1 == i) {
                               updateUserData();
                               spinner.setVisibility(View.INVISIBLE);
                           }else {
                               spinner.setVisibility(View.INVISIBLE);
                               Toast.makeText(getApplicationContext(), "Não foi possível editar o Perfil, tente novamente!", Toast.LENGTH_LONG).show();
                           }
                       }
                       else{
                          Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                       }
                   }catch (Exception e){
                       System.out.println("ERROR---> " + e.toString());
                   }
                }
            });
        }

        litersDialog.show();
    }
    private void showDlgCoupon() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialog_coupon, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(promptView);
        final AlertDialog litersDialog = builder.create();
        txtCupomDesc = promptView.findViewById(R.id.input_cupom_desc);
        txtCupomDesc.requestFocus();
        keyboard.showSoftInput(txtCupomDesc, 0);
        Button btnEnviar = promptView.findViewById(R.id.btn_coupon);
        if(null != btnEnviar){
            btnEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(null != txtCupomDesc.getText()){
                        litersDialog.dismiss();
                        spinner.setVisibility(View.VISIBLE);
                        getCoupon(txtCupomDesc.getText().toString().trim());
                    }
                   else{
                        Toast.makeText(getApplicationContext(), "Informe o valor do Cupom!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        litersDialog.show();
    }
    private void getCoupon(String nameCoupon){
     if(!cartBusiness.getIsDiscounted()){
         Call<Coupon> call = new InitializerApi(Configuration.getBaseUrl()).getCouponService().
                 getCoupon(cartBusiness.getIdCompany(), nameCoupon);
         call.enqueue(new Callback<Coupon>() {
             @Override
             public void onResponse(@NonNull Call<Coupon> call, @NonNull Response<Coupon> response) {
                 Coupon coupon = response.body();
                 if(null != coupon) {
                     cupomDesconto = coupon.getCupom().trim();
                    // cartBusiness.setIsDiscounted(true);
                     cartBusiness.setValorDescontoCupom(coupon.getDesconto());
                     spinner.setVisibility(View.GONE);
                     Toast.makeText(getApplicationContext(), "Cupom Adicionado com Sucesso!", Toast.LENGTH_LONG).show();
                     getTotalWithDiscount();
                 }
                 else{
                     cupomDesconto = "";
                     spinner.setVisibility(View.GONE);
                     Toast.makeText(getApplicationContext(), "Nenhum Cupom Encontrado!", Toast.LENGTH_LONG).show();
                 }
             }

             @Override
             public void onFailure(@NonNull Call<Coupon> call, @NonNull Throwable t) {
                 cupomDesconto = "";
                 spinner.setVisibility(View.GONE);
                 Toast.makeText(getApplicationContext(), "Falha ao consultar o Cupom!", Toast.LENGTH_LONG).show();
                 Log.e("onFaillure chamado ", t.getCause().getMessage());
             }
         });
     }
     else{
         Toast.makeText(getApplicationContext(), "Seu pedido já possui um cupom adicionado!", Toast.LENGTH_LONG).show();
     }
    }
    private void saveUser(User u){
        try{
            if(null != u){
                userRepository.insert(u);
            }
        }
        catch (Exception ex){
            System.out.println("FALHA AO SALVAR O USUÁRIO" );
        }
    }
    //private void makeOrderServer(final Order object, final OrderRepository repository, final long idOrderLocal)
    private void makeOrderServer(final Order object, final OrderRepository repository) {
        Call<String> call =  new InitializerApi(Configuration.getBaseUrl()).getOrderService().postOrderString(object);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        String ord = response.body();
                        spinner.setVisibility(View.GONE);
                        if(ord != null && ord.startsWith("S")) {
                            //UPDATE NUMBER ORDER SERVER
                            int idOrderServer = Integer.parseInt(ord.substring(2));
                            try {
                                object.setIdOrderServer(idOrderServer);
                                final long idOrder = repository.insert(object);
                                ProductDao productRepository = TaComSedeDatabase.getInstance(getApplicationContext()).getProductDao();
                               /* for(ItemCart itemCart : object.getLstProduct().iterator().next().setIdOrderFK();){
                                    //  prod.setIdOrderFK(idOrder);
                                }*/
                               // object.getLstProduct().parallelStream().forEach(p -> p.setIdOrderFK(idOrder));
                                for( int a = 0; a < object.getLstProduct().size(); a++){
                                    object.getLstProduct().get(a).setIdOrderFK(idOrder);
                                }
                               // productRepository.insert(lstProductOrder);
                                productRepository.insert(object.getLstProduct());

                                //SALVANDO O USUÁRIO NO BD
                                if(null == cartBusiness.getUser()) {
                                    saveUser(new User(txtName.getText().toString().trim(), txtFone.getText().toString().trim()));
                                    /*
                                    *  o.setFone(txtFone.getText().toString().trim());
                                    o.setName(txtName.getText().toString().trim());*/
                                }

                               // repository.updateIdOrderServer(idOrderLocal,idOrderServer );
                                /*System.out.println("ID DO SERVER-------" + idOrderServer);
                                System.out.println("ID LOCAL-------" + idOrderLocal);*/
                               // cartBusiness.setIsDiscounted(false);
                                String valorDescontadoCupom =  cartBusiness.getValorDescontoCupom().toString();
                                cartBusiness.setValorDescontoCupom(BigDecimal.ZERO);
                                Toast.makeText(getApplicationContext(), "Pedido Realizado com Sucesso!", Toast.LENGTH_LONG).show();
                                Intent myIntent = new Intent(getApplicationContext(), OrderActivity.class);
                                myIntent.putExtra("valorDescontadoCupom", valorDescontadoCupom );
                                startActivity(myIntent);
                            }catch (Exception ex){
                                Log.e("falha SALVAR PEDIDO ", ex.getMessage());
                                Toast.makeText(getApplicationContext(), "Falha ao salvar o Pedido!".concat(ord), Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Não foi possível realizar o Pedido!".concat(ord != null ? ord : null), Toast.LENGTH_LONG).show();
                        }
                    }else{
                        spinner.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Não foi possível realizar o Pedido!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    spinner.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Não foi possível realizar o Pedido!\n".concat(response.errorBody() != null ? response.errorBody().toString() : null), Toast.LENGTH_LONG).show();
                   }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                spinner.setVisibility(View.GONE);
                System.out.println("Falha no Salvar: " + t.toString());
                Toast.makeText(getApplicationContext(),  t.toString(), Toast.LENGTH_LONG).show();
               // Log.e("onFaillure chamado ", t.toString());
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_order_adress, menu);
        return true;
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_back) {
            spinner.setVisibility(View.GONE);
            finish();
            return true;
        }
        /*if (id == R.id.action_cart) {
            Intent myIntent = new Intent(this, CartActivity.class);
            startActivity(myIntent);
        }*/
        return super.onOptionsItemSelected(item);
    }

    private boolean validateName() {
        if (txtName.getText().toString().trim().isEmpty()) {
            iptLytName.setError(getString(R.string.err_msg_name));
           // Toast.makeText(getApplicationContext(), "O Nome é obrigatório!", Toast.LENGTH_SHORT).show();
          // txtName.requestFocus();
            requestFocus(txtName);
            return false;
        } else {
            iptLytName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFone() {
        if (txtFone.getText().toString().trim().isEmpty()) {
            iptLytFone.setError(getString(R.string.err_msg_fone));
            requestFocus(txtFone);
           // Toast.makeText(getApplicationContext(), "O Telefone é obrigatório!", Toast.LENGTH_SHORT).show();
           // requestFocus(txtFone);
            return false;
        } else {
            iptLytFone.setErrorEnabled(false);
            return true;
        }
    }

   /* private boolean validateFone() {
        String fone = txtFone.getText().toString().trim();
        if (fone.isEmpty() || !isValidEmail(fone)) {
            iptLytFone.setError(getString(R.string.err_msg_email));
            requestFocus(txtFone);
            return false;
        } else {
            iptLytFone.setErrorEnabled(false);
            return true;
        }
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }*/

    private boolean validateNumber() {
        if (txtNumber.getText().toString().trim().isEmpty()) {
            iptLytNumber.setError(getString(R.string.err_msg_password));
            requestFocus(txtNumber);
            return false;
        } else {
            iptLytNumber.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePayment() {
        if (null == formaPag || formaPag.trim().isEmpty()) {
           // divTitleFormaPgto.setError(getString(R.string.err_msg_payment));
            Toast.makeText(getApplicationContext(), "Escolha uma opção de Pagamento!", Toast.LENGTH_LONG).show();
            requestFocus(divTitleFormaPgto);
            return false;
        } else {
            return true;
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;
        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_fone:
                    validateFone();
                    break;
                case R.id.input_number:
                    validateNumber();
                    break;
            }
        }
    }

}
