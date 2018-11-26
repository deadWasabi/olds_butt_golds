package com.accenture.SmartOffice.service.impl;

import com.accenture.SmartOffice.customException.ObjectNotFoundExeption;
import com.accenture.SmartOffice.dao.entity.Office;
import com.accenture.SmartOffice.dao.entity.User;
import com.accenture.SmartOffice.dao.entity.WorkSpace;
import com.accenture.SmartOffice.dao.repository.OfficeRepository;
import com.accenture.SmartOffice.dao.repository.UserRepository;
import com.accenture.SmartOffice.dao.repository.WorkSpaceRepository;
import com.accenture.SmartOffice.model.web.WebOfficeFullModel;
import com.accenture.SmartOffice.model.web.WebOfficeModelViewModel;
import com.accenture.SmartOffice.model.web.WebUserSwapModel;
import com.accenture.SmartOffice.model.web.WebWorkspaceModel;
import com.accenture.SmartOffice.utilits.MappingHelper;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.SmartOffice.service.OfficeService;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private WorkSpaceRepository workSpaceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public WebOfficeFullModel getOfficeDetail(Long id) {
        Office office = officeRepository.getOne(id);
        if (office == null) {
            throw new ObjectNotFoundExeption(String.format("Не найден офис с данным ID=%s", id));
        }
        WebOfficeFullModel webOfficeFullModel = dozerBeanMapper.map(office, WebOfficeFullModel.class);
        office.getWorkSpace().forEach(s -> webOfficeFullModel.getWorkspaces().add(dozerBeanMapper.map(s, WebWorkspaceModel.class)));
        return webOfficeFullModel;
    }


    @Override
    public void setUserWorkspace(WebWorkspaceModel webWorkspaceModel) {
        WorkSpace workSpace = workSpaceRepository.findByXPositionAndYPositionAndOfficeId(
                webWorkspaceModel.getxPosition(),
                webWorkspaceModel.getyPosition(),
                webWorkspaceModel.getOfficeId()

        );
        if (workSpace != null) {
            User user = userRepository.getOne(webWorkspaceModel.getUserId());
            if (user == null) {
                throw new IllegalArgumentException("Невозможно отредактировать рабочее место, так как пользователь с данным Id=%d не найдет");
            }
            workSpace.setUser(user);
        }
        throw new ObjectNotFoundExeption(String.format(
                "Рабочее место в офисе с id=%d по данным координатам {%d,%d} не найдено",
                webWorkspaceModel.getOfficeId(),
                webWorkspaceModel.getxPosition(),
                webWorkspaceModel.getyPosition()
        ));
    }

    @Override
    public void deleteUserWorkspace(Long userWorkspaceId) {
        WorkSpace workSpace = workSpaceRepository.getOne(userWorkspaceId);
        if (workSpace == null) {
            throw new ObjectNotFoundExeption(String.format("Рабочее место с данным id=%d неудалось удалить", userWorkspaceId));
        }
        workSpace.setUser(null);
        workSpaceRepository.save(workSpace);
    }

    @Override
    public void swapUsers(WebUserSwapModel webUserSwapModel) {
        WorkSpace workSpaceFirstUser = workSpaceRepository.findByUserId(webUserSwapModel.getFirstUserId());
        WorkSpace workSpaceSecondUser = workSpaceRepository.findByUserId(webUserSwapModel.getSecondUserId());
        if (workSpaceFirstUser == null || workSpaceSecondUser == null) {
            throw new IllegalArgumentException("Один или оба пользователей не найдены");
        }
        User firstUser = workSpaceFirstUser.getUser();
        workSpaceFirstUser.setUser(workSpaceSecondUser.getUser());
        workSpaceSecondUser.setUser(firstUser);
        workSpaceRepository.save(workSpaceFirstUser);
        workSpaceRepository.save(workSpaceSecondUser);
    }

    @Override
    public List<WebOfficeModelViewModel> getOffices() {
        return MappingHelper.map(officeRepository.findAll(), WebOfficeModelViewModel.class);
    }
}
