package ru.tvstu.AccountingSystemHousingServices.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.tvstu.AccountingSystemHousingServices.customException.ObjectNotFoundExeption;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.Apartment;
import ru.tvstu.AccountingSystemHousingServices.dao.repository.ApartmentRepository;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentViewFullModel;
import ru.tvstu.AccountingSystemHousingServices.model.web.WebApartmentViewModel;
import ru.tvstu.AccountingSystemHousingServices.service.ApartmentService;
import ru.tvstu.AccountingSystemHousingServices.utilits.MappingHelper;

import java.util.List;

public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public List<WebApartmentViewModel> getWebApartmentViewModels() {
        List<Apartment> apartments = apartmentRepository.getApartmentsByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        return MappingHelper.map(apartments, WebApartmentViewModel.class);
    }

    @Override
    public WebApartmentViewFullModel getWebApartmentViewModelById(Long id) {
        Apartment apartment = apartmentRepository.getOne(id);
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(apartment.getOwner().getLogin())) {
            return dozerBeanMapper.map(apartment, WebApartmentViewFullModel.class);
        } else {
            throw new ObjectNotFoundExeption("Apartment by id - " + id + "not fount for user - " + SecurityContextHolder.getContext().getAuthentication().getName());
        }
    }

    @Override
    public WebApartmentViewFullModel createOrUpdateWebApartmentViewModel(WebApartmentViewFullModel webApartmentViewFullModel) {
        Apartment apartment = dozerBeanMapper.map(webApartmentViewFullModel, Apartment.class);
        return dozerBeanMapper.map(apartmentRepository.save(apartment), WebApartmentViewFullModel.class);
    }
}
