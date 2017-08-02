package org.kryptonmlt.securecredit.web;

import java.util.Collection;
import java.util.List;
import org.kryptonmlt.securecredit.model.CreditCard;
import org.kryptonmlt.securecredit.model.User;
import org.kryptonmlt.securecredit.repository.CreditCardRepository;
import org.kryptonmlt.securecredit.repository.UserRepository;
import org.kryptonmlt.securecredit.utils.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes credit cards stored in database to the relevant user
 *
 * @author Kurt
 */
@RestController()
@RequestMapping("/creditcard")
public class CreditCardController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * retrieves credit cards
     *
     * @param authentication logged in user
     * @return all credit cards of user
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<CreditCard> getCreditCards(Authentication authentication) {
        LOGGER.debug("Getting credit cards for user {}", authentication.getName());
        if (AuthUtils.isAdmin((Collection<GrantedAuthority>) authentication.getAuthorities())) {
            return (List<CreditCard>) creditCardRepository.findAll();
        }
        User user = userRepository.findByUsername(authentication.getName());
        return (List<CreditCard>) creditCardRepository.findByUser(user);
    }

    /**
     * Retrieves 1 Credit Card
     *
     * @param creditCardId Credit Card Id
     * @param authentication logged in user
     * @return the corresponding Credit Card
     */
    @RequestMapping(value = "/{creditCardId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable long creditCardId, Authentication authentication) {
        LOGGER.debug("Getting credit card: {}", creditCardId);
        CreditCard currentCreditCard = creditCardRepository.findOne(creditCardId);
        //make sure logged in user is owner of credit card
        if (!AuthUtils.isAdmin((Collection<GrantedAuthority>) authentication.getAuthorities()) && !currentCreditCard.getUser().getUsername().equals(authentication.getName())) {
            LOGGER.warn("User with {} tried accessing forbidden credit card {}", authentication.getName(), creditCardId);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(currentCreditCard, HttpStatus.OK);
    }

    /**
     *
     * @param creditCardForm
     * @param bindingResult
     * @param model
     * @param authentication
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createCreditCard(@ModelAttribute("creditCardForm") CreditCard creditCardForm, BindingResult bindingResult, Model model, Authentication authentication) {
        LOGGER.debug("Saving credit card: {}", creditCardForm.getNumber());
        User user = userRepository.findByUsername(authentication.getName());
        creditCardForm.setUser(user);
        creditCardRepository.save(creditCardForm);
    }

    /**
     * updates credit card
     *
     * @param creditCardId credit card it
     * @param creditCard credit card form data
     * @param authentication logged in user
     * @return updated card
     */
    @RequestMapping(value = "/{creditCardId}", method = RequestMethod.PUT)
    public ResponseEntity<CreditCard> updateCreditCard(@PathVariable("creditCardId") long creditCardId, @ModelAttribute("creditCardForm") CreditCard creditCard, Authentication authentication) {
        LOGGER.debug("Updating User {}" + creditCardId);

        CreditCard currentCreditCard = creditCardRepository.findOne(creditCardId);
        if (currentCreditCard == null) {
            LOGGER.warn("Credit Card with {} not found", creditCardId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //make sure logged in user is owner of credit card or he is admin
        if (!AuthUtils.isAdmin((Collection<GrantedAuthority>) authentication.getAuthorities()) && !currentCreditCard.getUser().getUsername().equals(authentication.getName())) {
            LOGGER.warn("User with {} tried accessing forbidden credit card {}", authentication.getName(), creditCardId);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        currentCreditCard.setMonth(creditCard.getMonth());
        currentCreditCard.setYear(creditCard.getYear());

        creditCardRepository.save(currentCreditCard);
        return new ResponseEntity<>(currentCreditCard, HttpStatus.OK);
    }

}
