// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package net.sepman.dice.repository;

import net.sepman.dice.domain.DiceThrow;
import net.sepman.dice.repository.ThrowRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect ThrowRepository_Roo_Jpa_Repository {
    
    declare parents: ThrowRepository extends JpaRepository<DiceThrow, Long>;
    
    declare parents: ThrowRepository extends JpaSpecificationExecutor<DiceThrow>;
    
    declare @type: ThrowRepository: @Repository;
    
}
