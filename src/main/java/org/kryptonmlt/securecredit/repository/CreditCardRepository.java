package org.kryptonmlt.securecredit.repository;

import java.util.List;
import org.kryptonmlt.securecredit.model.CreditCard;
import org.kryptonmlt.securecredit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    List<CreditCard> findByUser(User user);
}
