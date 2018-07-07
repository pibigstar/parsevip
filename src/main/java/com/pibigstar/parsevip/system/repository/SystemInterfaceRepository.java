package com.pibigstar.parsevip.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pibigstar.parsevip.system.domain.SystemInterface;

public interface SystemInterfaceRepository extends JpaRepository<SystemInterface, Long>{

	
	SystemInterface findByInterfaceName(String name);
}
