package com.Company.Repository;

import com.Brand.Model.Brand;
import com.Company.Model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company,Long> {
    @Async
    Company findByIdCompany(long idCompany);
    @Async
    List<Company> findByCidade_Name(String name);
    @Async
    List<Company> findByCidade_IdCity(long idCity);
    @Procedure("somar")
    int testSum(Integer a, Integer b);
    /*@Procedure("procedureFindAll")
    //@Query(nativeQuery = true,value = "getcomps")  // call store procedure
    // Object[] hope4(Integer a) ;
   // ResultSet testCompanies(Integer a);
     //List<Object> testCompanies(Integer a); ResultSet
    //SqlResultSetMapping testCompanies(Integer a);
   // List<Company> testCompanies(Integer a);
    Object[] testCompanies(Integer a);*/
   // List<Object> testCompanies(Integer a);
   @Procedure("hope8")
   BigDecimal testCaraio(Integer a);
   @Async
   Company findByCnpj(String cnpj);
  /* @Async
   @Query("select p from Product p where p.idCompany =?1 and p.productName like %?2% order by p.productName")
   Page<Brand> findByIdCompany(long idCompany, Pageable pageable);*/
   /* @Async
    @Query("select p from Company p  join fetch p.brands where p.idCompany =?1 ")
    Company getCompanyBrands(long idCompany);*/
    // SELECT p.idProduto, p.nomeProduto,p.produtoSKU,p.produtoParentSKU,p.pesoProduto,p.cdbProduto,p.ncm,p.exProdutoIbpt,
    // p.precoCustoProduto,p.unidadeEstoqueProduto, p.precoUnitarioProduto,p.descontoVista, p.fkIdDisponibilidadeProduto,
    // p.fkIdLocalProdutoDisponivel, p.fkIdMarca,p.fkIdTipoDescontoVista,p.fkStatusProduto,ppp.valorDesconto,
    // ppp.fkTipoDesconto,conficms.idSitucaoTributaria,pt.fkIdSitTribICMS,pt.fkIdSitTribPis,pt.codTpCalcPisST,
    // pt.codTpCalcCofins, pt.codTpCalcPis,pt.fkIdSitTribCofins,pt.codTpCalcCofinsST,icm.codRegimeICMS,
    // icm.fkIdOrigProdICMS,icm.modDetBcIcms,icm.redBcIcms, icm.aliqIcms,icm.modDetBcIcmsSt,icm.redBcIcmsSt,
    // icm.margValAdcIcmsSt,icm.aliqIcmsSt,icm.bcOpPropria,icm.ufIcmsStDevidoOp,icm.motDesoneracaoIcms,
    // pt.fkIdSitTribIPI FROM produto p
    // LEFT JOIN produtoprecopromocao pp on pp.idProduto = p.idProduto
    // LEFT JOIN promocaopreco ppp on ppp.idPromocao = pp.idPromocao AND ppp.dataFim >= ?
    // LEFT JOIN produtotributos pt ON p.idProduto = pt.idProduto
    // LEFT JOIN configuracaoicms conficms ON conficms.idSitucaoTributariaPk = pt.fkIdSitTribICMS
    // LEFT JOIN icmsprodutos icm on icm.idProduto = p.idProduto
    // WHERE (p.nomeProduto LIKE ? OR p.produtoSKU LIKE ? OR p.cdbProduto LIKE ? OR p.idProduto = ?)
    // AND ((p.fkIdLocalProdutoDisponivel = 1 OR p.fkIdLocalProdutoDisponivel = 2) AND p.fkStatusProduto = 1)
    // ORDER BY p.nomeProduto"


}
