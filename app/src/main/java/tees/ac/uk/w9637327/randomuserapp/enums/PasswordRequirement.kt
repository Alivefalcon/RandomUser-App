package tees.ac.uk.w9637327.randomuserapp.enums

import androidx.annotation.StringRes
import tees.ac.uk.w9637327.randomuserapp.R

enum class PasswordRequirement(
    @StringRes val label: Int
) {
    CAPITAL_LETTER(R.string.password_requirements_capital),
    NUMBER(R.string.password_requirements_digit),
    EIGHT_CHARACTERS(R.string.password_requirements_characters)
}