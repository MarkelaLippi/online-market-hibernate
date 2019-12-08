package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.servicemodule.services.UserPasswordGenerator;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordGeneratorImpl implements UserPasswordGenerator {
    @Override
    public String generateRandomPassword() {
        {
            PasswordGenerator passwordGenerator = new PasswordGenerator();
            CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
            CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
            lowerCaseRule.setNumberOfCharacters(2);

            CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
            CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
            upperCaseRule.setNumberOfCharacters(2);

            CharacterData digitChars = EnglishCharacterData.Digit;
            CharacterRule digitRule = new CharacterRule(digitChars);
            digitRule.setNumberOfCharacters(2);

            CharacterData specialChars = EnglishCharacterData.Special;
            CharacterRule specialCharsRule = new CharacterRule(specialChars);
            specialCharsRule.setNumberOfCharacters(2);

            CharacterRule splCharRule = new CharacterRule(specialChars);
            splCharRule.setNumberOfCharacters(2);

            return passwordGenerator.generatePassword(10, lowerCaseRule, upperCaseRule, digitRule, splCharRule);
        }
    }
}
