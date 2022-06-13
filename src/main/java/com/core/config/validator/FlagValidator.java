package com.core.config.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 状态约束校验器,即是true或false的约束
 * Created by macro on 2018/4/26.
 */
public class FlagValidator implements ConstraintValidator<FlagInterface,Integer> {
    private String[] values;
    @Override
    public void initialize(FlagInterface flagInterface) {
        System.out.println("状态约束校验器-1-");
        this.values = flagInterface.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("状态约束校验器-2-");
        boolean isValid = false;
        if(value==null){
            //当状态为空时使用默认值
            return true;
        }
        for(int i=0;i<values.length;i++){
            if(values[i].equals(String.valueOf(value))){
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
