package com.jhelper.jserve.web.org;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import com.jhelper.jserve.web.entity.Org;

import org.assertj.core.api.Assertions;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrgTests {

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
        Org org = orgService.getOrg("101010");
        assertThat(org.getOrgCd()).isEqualTo("101010");
        assertThat(org.getUsers()).size().isGreaterThan(0);
    }

    @Test
    void orgAndUsers2() {
        List<Org> orgs = orgService.getAllWithUsers(Org.builder().orgCd("101010").build());
        assertThat(orgs).size().isEqualTo(1);
        assertThat(orgs.get(0).getUsers()).size().isGreaterThan(0);
    }

}
