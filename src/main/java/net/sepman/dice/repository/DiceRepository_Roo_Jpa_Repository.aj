// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package net.sepman.dice.repository;

import net.sepman.dice.domain.Die;
import net.sepman.dice.repository.DiceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect DiceRepository_Roo_Jpa_Repository {
    
    declare parents: DiceRepository extends JpaRepository<Die, Long>;
    
    declare parents: DiceRepository extends JpaSpecificationExecutor<Die>;
    
    declare @type: DiceRepository: @Repository;
    
}
