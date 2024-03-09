package com.uktamjon.sodikov.repositories;

import com.uktamjon.sodikov.domains.mongoDbSummary.TrainerDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrainerDocumentRepositoryTest {

    @Mock
    private TrainerDocumentRepository trainerDocumentRepository;

    @Test
    public void testFindByUsername_found() throws Exception {
        String username = "test_user";
        TrainerDocument expectedTrainerDocument = new TrainerDocument();
        expectedTrainerDocument.setUsername(username);

        Mockito.when(trainerDocumentRepository.findByUsername(username)).thenReturn(expectedTrainerDocument);

        TrainerDocument actualTrainerDocument = trainerDocumentRepository.findByUsername(username);

        assertEquals(expectedTrainerDocument, actualTrainerDocument);
        verify(trainerDocumentRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testFindByUsername_notFound() throws Exception {
        String username = "test_user";

        Mockito.when(trainerDocumentRepository.findByUsername(username)).thenReturn(null);

        TrainerDocument actualTrainerDocument = trainerDocumentRepository.findByUsername(username);

        assertNull(actualTrainerDocument);
        verify(trainerDocumentRepository, times(1)).findByUsername(username);
    }
}
