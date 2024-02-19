    package com.uktamjon.sodikov.summary;

    import com.uktamjon.sodikov.domains.Trainer;
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
