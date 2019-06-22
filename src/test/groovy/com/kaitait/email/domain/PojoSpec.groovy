package com.kaitait.email.domain

import com.openpojo.reflection.impl.PojoClassFactory
import com.openpojo.validation.Validator
import com.openpojo.validation.ValidatorBuilder
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule
import com.openpojo.validation.rule.impl.GetterMustExistRule
import com.openpojo.validation.rule.impl.NoFieldShadowingRule
import com.openpojo.validation.rule.impl.NoNestedClassRule
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule
import com.openpojo.validation.rule.impl.SerializableMustHaveSerialVersionUIDRule
import com.openpojo.validation.rule.impl.SetterMustExistRule
import com.openpojo.validation.test.impl.GetterTester
import com.openpojo.validation.test.impl.SetterTester
import spock.lang.Specification
import spock.lang.Unroll

class PojoSpec extends Specification {
    static List<Class> classesToTest = [User, EmailedUser]
    static Validator validator

    def setupSpec() {
        validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new NoFieldShadowingRule())
                .with(new NoNestedClassRule())
                .with(new NoPublicFieldsExceptStaticFinalRule())
                .with(new NoStaticExceptFinalRule())
                .with(new SerializableMustHaveSerialVersionUIDRule())
                .with(new EqualsAndHashCodeMatchRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build()
    }

    @Unroll
    def "class #clazz.name fulfils the POJO contract"() {
        expect:
        validator.validate(PojoClassFactory.getPojoClass(clazz))

        where:
        clazz << classesToTest
    }
}
