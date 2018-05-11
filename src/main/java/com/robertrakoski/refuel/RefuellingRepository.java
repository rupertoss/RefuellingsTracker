package com.robertrakoski.refuel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RefuellingRepository extends CrudRepository<Refuelling, Long> {

}
