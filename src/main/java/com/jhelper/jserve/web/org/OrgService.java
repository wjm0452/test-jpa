package com.jhelper.jserve.web.org;

import java.util.List;

import com.jhelper.jserve.web.entity.Org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class OrgService {

    @Autowired
    OrgRepository orgRepository;

    public List<Org> getAll() {
        return orgRepository.findAll();
    }

    public List<Org> getAll(Org org) {
        return orgRepository.findAll(Example.of(org));
    }

    public Org getOrg(String orgCd) {
        return orgRepository.findById(orgCd).get();
    }

    public List<Org> getAllWithUsers(String orgCd) {
        return orgRepository.findAllWithUsers(orgCd);
    }

    public List<Org> getAllWithUsers(Org org) {
        return orgRepository.findAllWithUsers(org);
    }
}
