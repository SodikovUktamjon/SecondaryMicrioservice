    package com.example.secondarymicrioservice.summary;

    import com.example.secondarymicrioservice.Trainer;
    import lombok.*;

    import java.util.List;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Summary {

        private Trainer trainer;
        private List<YearlySummary> yearlySummary;

    }
