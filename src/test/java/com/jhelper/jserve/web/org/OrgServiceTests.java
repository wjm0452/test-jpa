package com.jhelper.jserve.web.org;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import com.jhelper.jserve.web.entity.Org;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrgServiceTests {

    @Autowired
    OrgService orgService;

    @Test
    void org() {
        List<Org> orgs = orgService.getAll();
        assertThat(orgs).size().isGreaterThan(0);
        assertThatThrownBy(() -> {
            assertThat(orgs.get(0).getUsers()).size().isGreaterThan(0);
        }).isInstanceOf(LazyInitializationException.class);
    }

    @Test
    void orgAndUsers() {
        Org org = orgService.getOrg("1010");
        assertThat(org.getOrgCd()).isEqualTo("1010");
        assertThat(org.getUsers()).size().isEqualTo(0);
    }

    @Test
    void notContainsUsers() {
        List<Org> orgs = orgService.getAllWithUsers("1010");
        assertThat(orgs).size().isEqualTo(1);
        assertThat(orgs.get(0).getUsers()).size().isEqualTo(0);
    }

    @Test
    void orgs() {
        List<Org> orgs = orgService.getAllWithUsers("101010");
        assertThat(orgs).size().isEqualTo(1);
        assertThat(orgs.get(0).getUsers()).size().isGreaterThan(0);
    }

    @Test
    void orgsByOrg() {
        List<Org> orgs = orgService.getAllWithUsers(Org.builder().orgCd("101010").build());
        assertThat(orgs).size().isEqualTo(1);
        assertThat(orgs.get(0).getUsers()).size().isGreaterThan(0);
    }

}
