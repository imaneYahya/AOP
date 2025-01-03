package com.Imane.ASPECTS;

import com.Imane.METIER.Compte;
import com.Imane.METIER.IMetierBanque;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PatchRetraitAspect {
    @Pointcut("execution(* com.Imane.METIER.IMetierBanqueImpl.retirer(..) )")
    public void pc1(){ }
    @Around("pc1() && args(code,montant)")
    public Object autourRetirer(Long code,double montant,ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable {
        IMetierBanque metierBanque=(IMetierBanque) joinPoint.getTarget();
        Compte compte=metierBanque.consulter(code);
        if(compte.getSolde()<montant) throw new RuntimeException("Balance not sufficient ");
        return  proceedingJoinPoint.proceed();
    }
}
