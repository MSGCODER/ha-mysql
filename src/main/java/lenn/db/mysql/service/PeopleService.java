package lenn.db.mysql.service;

import lenn.db.mysql.annotation.DataSource;
import lenn.db.mysql.dao.PeopleDao;
import lenn.db.mysql.dao.entities.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lenn on 16/6/1.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class PeopleService {
    @Autowired
    private PeopleDao peopleDao;

    @DataSource("master")
    public void create(){
        peopleDao.save(new People());
    }

    @DataSource("slave")
    public void save(){
        peopleDao.save(new People());
    }
}
