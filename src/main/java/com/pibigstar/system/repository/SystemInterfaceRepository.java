package com.pibigstar.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pibigstar.system.domain.SystemInterface;

public interface SystemInterfaceRepository extends JpaRepository<SystemInterface, Long>{

	
	SystemInterface findByInterfaceName(String name);
}
