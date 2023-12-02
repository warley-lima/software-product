package com.Module.Service;

import com.Module.Model.Module;
import com.Module.Repository.ModuleRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Service
public class ModuleServiceImp implements ModuleService{

    @Inject
    private  ModuleRepository repository;

    @Transactional
    @Override
    public Module getModuleById(long id) {
        return  repository.getOne(id);
    }

    @Transactional
    @Override
    public void save(Module module) {
        repository.save(module);
    }

    @Override
    public void loadModules(){
        Module m1 = new Module();
        m1.setName("Users");
        save(m1);
        Module m2 = new Module("Clients");
        save(m2);
        Module m3 = new Module("Products");
        save(m3);
        Module m4 = new Module("Billing");
        save(m4);
        Module m5 = new Module("Ordered");
        save(m5);


    }
}
