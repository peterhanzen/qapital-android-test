package com.pha.qapital.network.models

import com.google.gson.Gson
import spock.lang.Specification

/**
 * Created by pha on 2017-12-03.
 */
class QapStatusTest extends Specification {

    def '"active" should be deserialized as ACTIVE'() {

        given:
        def string = "active"
        def expected = QapStatus.ACTIVE

        when:
        def result = (new Gson()).fromJson(string, QapStatus.class)

        then:
        result == expected

    }

    def '"passive" should be deserialized PASSIVE'() {

        given:
        def string = "passive"
        def expected = QapStatus.PASSIVE

        when:
        def result = (new Gson()).fromJson(string, QapStatus.class)

        then:
        result == expected

    }

    def 'ACTIVE should be serialized as "active"'() {

        given:
        def status = QapStatus.ACTIVE
        def expected = '"active"'

        when:
        def result = (new Gson()).toJson(status)

        then:
        result == expected

    }

    def 'PASSIVE should be serialized as "passive"'() {

        given:
        def status = QapStatus.PASSIVE
        def expected = '"passive"'

        when:
        def result = (new Gson()).toJson(status)

        then:
        result == expected

    }

}
