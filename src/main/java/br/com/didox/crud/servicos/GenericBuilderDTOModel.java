package br.com.didox.crud.servicos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GenericBuilderDTOModel<T> {
    private Class<T> genericType;

    public GenericBuilderDTOModel(Class<T> type) {
      this.genericType = type;
    }

    public T build(Object objectDTO) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return build(objectDTO, null);
    }

    public T build(Object objectDTO, Object objectModel) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        if(objectModel == null) objectModel = genericType.getDeclaredConstructor().newInstance();

        for (var methodDTO : objectDTO.getClass().getMethods()) {
            if(methodDTO.getName().contains("get")){
                String prop = methodDTO.getName().replace("get", "");
                var setMethod = this.getMethod(objectModel, "set" + prop);
                if(setMethod != null){
                    var value = methodDTO.invoke(objectDTO);
                    setMethod.invoke(objectModel, value);
                }
            }
        }

        return (T)objectModel;
    }

    public Method getMethod(Object objectModel, String methodName) {
        var methods = objectModel.getClass().getMethods();
        for (var method : methods) {
            if( method.getName().equals(methodName)){
                return method;
            }
        }
        return null;
    }
}
