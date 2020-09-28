package ch.helvetia.evn.controller;

import ch.helvetia.evn.microservices.adapter.CLSAdapterService;
import ch.helvetia.evn.pojo.EVN;
import ch.helvetia.evn.pojo.Nachweis;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class CLSAdapterServiceTest {

    @Inject
    CLSAdapterService service;

    @Test
    public void withNachweis_process_expectEVNIdSet() {
        // Arrange
        Nachweis nachweis = new Nachweis();

        // Act
        EVN evn = service.process(nachweis);

        // Assert
        assertThat(evn.evnId, is(notNullValue()));
    }

    @Test
    public void withNachweisMocked_process_expectEVNIdSet() {
        // Arrange
        mockCLSService();
        Nachweis nachweis = new Nachweis();

        // Act
        EVN evn = service.process(nachweis);

        // Assert
        assertThat(evn.evnId, is("any"));
    }

    private void mockCLSService() {
        CLSAdapterService mock = Mockito.mock(CLSAdapterService.class);
        EVN evnReturned =  new EVN();
        evnReturned.evnId = "any";
        Mockito.when(mock.process(Mockito.any(Nachweis.class))).thenReturn(evnReturned);
        QuarkusMock.installMockForType(mock, CLSAdapterService.class);
    }

}
