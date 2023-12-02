package com.Module.Service;

import com.Module.Model.Module;

//@Service
public interface ModuleService {
    public abstract Module getModuleById(long id);
    public abstract void save(Module module);
    public abstract void loadModules();
}
