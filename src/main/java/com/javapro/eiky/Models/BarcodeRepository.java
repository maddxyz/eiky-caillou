package com.javapro.eiky.Models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarcodeRepository extends CrudRepository<Barcode, String> {}