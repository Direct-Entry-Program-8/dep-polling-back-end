package lk.ijse.dep8.polling.service.custom.impl;

import lk.ijse.dep8.polling.dao.DAOFactory;
import lk.ijse.dep8.polling.dto.PollDTO;
import lk.ijse.dep8.polling.service.custom.PollService;
import lk.ijse.dep8.polling.service.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PollServiceImpl implements PollService {

    @Override
    public List<PollDTO> listAllPolls() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        em.close();
    }

    @Override
    public PollDTO getPoll(int id) {
        return null;
    }

    @Override
    public PollDTO savePoll(PollDTO dto) {
        return null;
    }

    @Override
    public void updatePoll(PollDTO dto) {

    }

    @Override
    public void deletePoll(int id) {

    }
}
