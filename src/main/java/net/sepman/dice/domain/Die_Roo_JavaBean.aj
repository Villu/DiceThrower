// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package net.sepman.dice.domain;

import net.sepman.dice.domain.Die;

privileged aspect Die_Roo_JavaBean {
    
    public int Die.getSides() {
        return this.sides;
    }
    
    public void Die.setSides(int sides) {
        this.sides = sides;
    }
    
    public int Die.getThrowResult() {
        return this.throwResult;
    }
    
    public void Die.setThrowResult(int throwResult) {
        this.throwResult = throwResult;
    }
    
    public int Die.getExploded() {
        return this.exploded;
    }
    
    public void Die.setExploded(int exploded) {
        this.exploded = exploded;
    }
    
    public boolean Die.isHit() {
        return this.hit;
    }
    
    public void Die.setHit(boolean hit) {
        this.hit = hit;
    }
    
}
