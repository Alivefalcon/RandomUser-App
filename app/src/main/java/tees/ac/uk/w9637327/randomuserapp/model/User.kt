package tees.ac.uk.w9637327.randomuserapp.model

class User {
    lateinit var name: Name
    lateinit var email: String
    lateinit var phone: String
    lateinit var gender: String
    lateinit var location: Location
    lateinit var picture: Picture
}

class Name {
    var title: String? = null
    var first: String? = null
    var last: String? = null

    override fun toString(): String {
        return "${this?.title + " " + (this?.first) + " " + (this?.last)}"
    }
}

class Location {
    var street: Street? = null
    var city: String? = null
    var state: String? = null
    var country: String? = null
    var postcode: String? = null

    override fun toString(): String {
        return return "${this?.street?.number.toString() + " " + this?.street?.name + " " + (this?.city) + " " + (this?.state) + " " + (this?.country)}"
    }
}

class Picture {
    var large: String? = null
    var medium: String? = null
    var thumbnail: String? = null
}

class Street {
    var number: Int = 0
    var name: String? = null
}

class Info {
    var seed: String? = null
    var results = 0
    var page = 0
    var version: String? = null
}