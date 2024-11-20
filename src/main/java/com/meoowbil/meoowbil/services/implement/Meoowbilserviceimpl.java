package com.meoowbil.meoowbil.services.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meoowbil.meoowbil.models.Meoowbil;
import com.meoowbil.meoowbil.repository.Meoowbilrepository;
import com.meoowbil.meoowbil.services.Meoowbilservice;

@Service
public class Meoowbilserviceimpl implements Meoowbilservice {
    private Meoowbilrepository meoowbilrepository;


    public Meoowbilserviceimpl(Meoowbilrepository meoowbilrepository) {
        this.meoowbilrepository = meoowbilrepository;
    }

    @Override
    public List<Meoowbil> getAllMeoowbils() {
       List<Meoowbil> meoowbils = meoowbilrepository.findAll();
       return meoowbils;
    }

    public void saveMeoowbil(Meoowbil meoowbil) {
        meoowbilrepository.save(meoowbil);
    }

}
