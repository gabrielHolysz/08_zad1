<img width="283" height="521" alt="image" src="https://github.com/user-attachments/assets/760b7473-45d4-4933-9d64-5053a1e0c7cb" />
<img width="444" height="756" alt="image" src="https://github.com/user-attachments/assets/e73d2d6f-7503-45db-a33b-c1371b0dc9f5" />


  Rejestracja kontraktu przez `registerForActivityResult()` musi nastąpić w fazie inicjalizacji aktywności (przed przejściem w stan `RESUMED`). Wywołanie jej wewnątrz obsługi kliknięcia przycisku naruszałoby cykl życia komponentu i skutkowało błędem `IllegalStateException`.


  Ekran wywołujący nie zmieni swojego stanu – formularz pozostanie wypełniony dotychczasowymi danymi. Dzieje się tak, ponieważ naciśnięcie przycisku Wstecz domyślnie ustawia wynik `RESULT_CANCELED`, który w naszym kodzie powoduje zamknięcie podsumowania bez wykonywania dodatkowych akcji.
