package com.robertrakoski.refuel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
