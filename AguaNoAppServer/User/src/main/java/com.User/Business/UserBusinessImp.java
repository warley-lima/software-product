package com.User.Business;

import com.Rule.Model.Rule;
import com.Rule.Repository.RuleRepository;
import com.User.Model.User;
import com.User.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

//@Service
@Component
public class UserBusinessImp implements UserBusiness {

    @Inject
    private UserRepository repository;
    @Inject
    private RuleRepository ruleRepository;

    @Transactional
    @Override
    public void save(User user) {
        List<Rule> lstRules = new ArrayList<>();
        user.setPerfil("USER");
        repository.save(user);
       /* Rule rUsers = new Rule();
        rUsers.setIdModuleFK(1);
        rUsers.setName("{C}");
        Rule rUsers2 = new Rule();
        rUsers2.setIdModuleFK(1);
        rUsers2.setName("{R}");

        Rule rUsers3 = new Rule();
        rUsers3.setIdModuleFK(1);
        rUsers3.setName("{U}");
        Rule rUsers4 = new Rule();
        rUsers4.setIdModuleFK(1);
        rUsers4.setName("{D}");

        Rule rClients = new Rule();
        rClients.setIdModuleFK(2);
        rClients.setName("{C}");
        Rule rClients2 = new Rule();
        rClients2.setIdModuleFK(2);
        rClients2.setName("{R}");
        Rule rClients3 = new Rule();
        rClients3.setIdModuleFK(2);
        rClients3.setName("{U}");
        Rule rClients4 = new Rule();
        rClients4.setIdModuleFK(2);
        rClients4.setName("{D}");

        Rule r1 = new Rule();
        r1.setIdModuleFK(3);
        r1.setName("{C}");
        Rule r2 = new Rule();
        r2.setIdModuleFK(3);
        r2.setName("{R}");
        Rule r3 = new Rule();
        r3.setIdModuleFK(3);
        r3.setName("{U}");
        Rule r4 = new Rule();
        r4.setIdModuleFK(3);
        r4.setName("{D}");

        Rule rCoupon = new Rule();
        rCoupon.setIdModuleFK(4);
        rCoupon.setName("{C}");
        Rule rCoupon2 = new Rule();
        rCoupon2.setIdModuleFK(4);
        rCoupon2.setName("{R}");
        Rule rCoupon3 = new Rule();
        rCoupon3.setIdModuleFK(4);
        rCoupon3.setName("{U}");
        Rule rCoupon4 = new Rule();
        rCoupon4.setIdModuleFK(4);
        rCoupon4.setName("{D}");

        Rule rOrder = new Rule();
        rOrder.setIdModuleFK(5);
        rOrder.setName("{C}");
        Rule rOrder2 = new Rule();
        rOrder2.setIdModuleFK(5);
        rOrder2.setName("{R}");
        Rule rOrder3 = new Rule();
        rOrder3.setIdModuleFK(5);
        rOrder3.setName("{U}");
        Rule rOrder4 = new Rule();
        rOrder4.setIdModuleFK(5);
        rOrder4.setName("{D}");*/


        long id = user.getId();

        Rule rUsers = new Rule();
        rUsers.setIdModuleFK(1);
        rUsers.setIdUserFK(id);
        rUsers.setName("{C}");
        Rule rUsers2 = new Rule();
        rUsers2.setIdModuleFK(1);
        rUsers2.setIdUserFK(id);
        rUsers2.setName("{R}");

        Rule rUsers3 = new Rule();
        rUsers3.setIdModuleFK(1);
        rUsers3.setIdUserFK(id);
        rUsers3.setName("{U}");
        Rule rUsers4 = new Rule();
        rUsers4.setIdModuleFK(1);
        rUsers4.setIdUserFK(id);
        rUsers4.setName("{D}");

        Rule rClients = new Rule();
        rClients.setIdModuleFK(2);
        rClients.setIdUserFK(id);
        rClients.setName("{C}");
        Rule rClients2 = new Rule();
        rClients2.setIdModuleFK(2);
        rClients2.setIdUserFK(id);
        rClients2.setName("{R}");
        Rule rClients3 = new Rule();
        rClients3.setIdModuleFK(2);
        rClients3.setIdUserFK(id);
        rClients3.setName("{U}");
        Rule rClients4 = new Rule();
        rClients4.setIdModuleFK(2);
        rClients4.setIdUserFK(id);
        rClients4.setName("{D}");

        Rule r1 = new Rule();
        r1.setIdModuleFK(3);
       // r1.setIdUserFK(user.getId());
        r1.setIdUserFK(id);
        r1.setName("{C}");
        Rule r2 = new Rule();
        r2.setIdModuleFK(3);
       // r2.setIdUserFK(user.getId());
        r2.setIdUserFK(id);
        r2.setName("{R}");
        Rule r3 = new Rule();
        r3.setIdModuleFK(3);
        //r3.setIdUserFK(user.getId());
        r3.setIdUserFK(id);
        r3.setName("{U}");
        Rule r4 = new Rule();
        r4.setIdModuleFK(3);
       // r4.setIdUserFK(user.getId());
        r4.setIdUserFK(id);
        r4.setName("{D}");

        Rule rCoupon = new Rule();
        rCoupon.setIdModuleFK(4);
        // rCoupon.setIdUserFK(user.getId());
        rCoupon.setIdUserFK(id);
        rCoupon.setName("{C}");
        Rule rCoupon2 = new Rule();
        rCoupon2.setIdModuleFK(4);
        //rCoupon2.setIdUserFK(user.getId());
        rCoupon2.setIdUserFK(id);
        rCoupon2.setName("{R}");
        Rule rCoupon3 = new Rule();
        rCoupon3.setIdModuleFK(4);
        // rCoupon3.setIdUserFK(user.getId());
        rCoupon3.setIdUserFK(id);
        rCoupon3.setName("{U}");
        Rule rCoupon4 = new Rule();
        rCoupon4.setIdModuleFK(4);
        // rCoupon4.setIdUserFK(user.getId());
        rCoupon4.setIdUserFK(id);
        rCoupon4.setName("{D}");

        Rule rOrder = new Rule();
        rOrder.setIdModuleFK(5);
       // rOrder.setIdUserFK(user.getId());
        rOrder.setIdUserFK(id);
        rOrder.setName("{C}");
        Rule rOrder2 = new Rule();
        rOrder2.setIdModuleFK(5);
        //rOrder2.setIdUserFK(user.getId());
        rOrder2.setIdUserFK(id);
        rOrder2.setName("{R}");
        Rule rOrder3 = new Rule();
        rOrder3.setIdModuleFK(5);
       // rOrder3.setIdUserFK(user.getId());
        rOrder3.setIdUserFK(id);
        rOrder3.setName("{U}");
        Rule rOrder4 = new Rule();
        rOrder4.setIdModuleFK(5);
       // rOrder4.setIdUserFK(user.getId());
        rOrder4.setIdUserFK(id);
        rOrder4.setName("{D}");

        lstRules.add(rUsers);
        lstRules.add(rUsers2);
        lstRules.add(rUsers3);
        lstRules.add(rUsers4);

        lstRules.add(rClients);
        lstRules.add(rClients2);
        lstRules.add(rClients3);
        lstRules.add(rClients4);

        lstRules.add(r1);
        lstRules.add(r2);
        lstRules.add(r3);
        lstRules.add(r4);

        lstRules.add(rOrder);
        lstRules.add(rOrder2);
        lstRules.add(rOrder3);
        lstRules.add(rOrder4);

        lstRules.add(rCoupon);
        lstRules.add(rCoupon2);
        lstRules.add(rCoupon3);
        lstRules.add(rCoupon4);

       /* user.setRules(lstRules);
        repository.save(user);*/
        ruleRepository.saveAll(lstRules);

    }

    @Transactional
    @Override
    public void update(User user) {
       // user.setRules(null);  , updatable = false, nullable = false
      //  repository.save(user);
        User u = repository.findById(user.getId());
        u.setName(user.getName());
        u.setUserName(user.getUserName());
        u.setPassword(user.getPassword());
        repository.saveAndFlush(u);
      //  repository.flush();
    }

    @Transactional
    @Override
    public void delete(long id) {
       /* if(ruleRepository.deleteAllByIdUserFK(id)) {

        }*/
      /* int ret = ruleRepository.deleteAllRulersUser(id);
        System.out.println("EXCLUSAO--->" + ret);*/
     //   System.out.println("EXCLUSAO--->" + id.getRules().size());
     //   ruleRepository.delete(id.getRules());
       // id.getRules().removeAll(id.getRules());
       /* for(int a = 0; a < id.getRules().size(); a++){
           // id.getRules().remove(a);
            System.out.println("EXCLUSAO--->" + a);
            ruleRepository.delete(id.getRules().get(a));
        }*/
       /*if(ruleRepository.deleteByIdUserFK(id)){
           ruleRepository.deleteByIdUserFK(id)
         repository.delete(id);
       }*/
        ruleRepository.deleteByIdUserFK(id);
        repository.deleteById(id);
    }

    @Override
    public Page<User> getUsersByIdCompany(long idCompany, Pageable pageable) {
        return repository.findByIdCompanyOrderByName(idCompany, pageable);
    }

    @Override
    public Page<User> getUserSearchByIdCompany(long idCompany, Pageable pageable, String name) {
        return repository.findByIdCompanyAndNameContainingIgnoreCase(idCompany, name, pageable);
    }

    @Override
    public User getUser(long id) {
        return repository.findById(id);
    }

    @Override
    public User getUserByMail(String userMail) {
        return repository.findByUserName(userMail);
    }
}
