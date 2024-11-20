package com.meoowbil.meoowbil.services;

import java.util.List;

import com.meoowbil.meoowbil.models.Meoowbil;


public interface Meoowbilservice {
    public List<Meoowbil> getAllMeoowbils();

    public void saveMeoowbil(Meoowbil meoowbil);

    
}
