package com.spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DI_Ex02 {

	/*

	# IoC(Inversion of Control)

	   - IoC(제어권의 역전)이란, 객체의 생성, 생명주기의 관리까지 모든 객체에 대한 제어권(개발자 > 스프링 프레임워크)이 바뀌었다는 것을 의미한다.
	   - 스프링 프레임워크에서 객체에 대한 생성 및 연결 및 생명주기를 관리할 수 있는 기능을 IoC 컨테이너를 이용하여 제공하고 있다.
	   - POJO의 생성, 초기화, 서비스, 소멸에 대한 작업을 처리한다.


	# 의존성 주입(Dependency Injection)

	    - 각 클래스간의 의존관계를 IoC 컨테이너가 자동으로 연결해주는 것
	   - 컨테이너가 흐름의 주체가 되어 애플리케이션 코드에 의존관계를 주입해 주는 것이다.
	   - 개발자들은 단지 설정파일에서 의존관계가 필요하다는 정보를 추가하면 된다.

	   [동작과정]
	   1) 구현 클래스의 정보를 xml(설정파일)에 기술 하거나 클래스에 어노테이션을 명시한다. 
	   2) Framework( IoC 컨테이너)에서 설정파일을 읽거나 어노테이션을 읽어들인다. 
	   3) 구현객체를 생성하며 서로 의존관계가 있는지 확인하여 객체를 연결한다.

	    @Autowired   : 스프링 어노테이션      , 타입에 맞추어서 연결   
	    @Inject      : 자바 표준 어노테이션   , 타입에 맞추어서 연결   , Dependency추가 이후 사용 가능
	    @Resource   : 자바 표준 어노테이션   , 이름에 맞추어서 연결

	*/

	
	@RequestMapping(value="/diEx02")
	public String diEx02() {
		
		ApplicationContext context =  new GenericXmlApplicationContext("classpath:CharacterApplicationContext.xml");
		
		// 방법 1,2,3)
		//CharacterManager2 cm2 = context.getBean("characterManager2",CharacterManager2.class); // getBean("bean의 id값",클래스명.class);
		//CharacterManager2 cm2 = (CharacterManager2)context.getBean("characterManager2");        // (클래스명)getBean("bean의 id값");
		CharacterManager2 cm2 = context.getBean(CharacterManager2.class);   // 클래스가 유일한 경우 bean의 id값을 생략 할 수 있다.
		
		
		System.out.println("\n======= XML파일을 통한 객체 주입 =======");
		Warrior warrior1 = context.getBean("warrior" , Warrior.class);
		Wizard wizard1   = context.getBean("wizard" , Wizard.class);
		Hunter hunter1_1 = context.getBean("hunter1" , Hunter.class);
		Hunter hunter1_2 = context.getBean("hunter2" , Hunter.class);
		
		cm2.setWarrior(warrior1);
		cm2.printCharacter1Info();
		
		cm2.setWizard(wizard1);
		cm2.printCharacter2Info();
		
		cm2.setHunter(hunter1_1);
		cm2.printCharacter3Info();
		
		cm2.setHunter(hunter1_2);
		cm2.printCharacter3Info();
		
		System.out.println("===========================");
		// xml파일에 설정값이 1개만 있으면 bean의 id 생략가능
		Warrior warrior2 = context.getBean(Warrior.class);
		Wizard wizard2 = context.getBean(Wizard.class);
		// Hunter hunter2 = context.getBean(Hunter.class); // bean id 생략 불가
		
		cm2.setWarrior(warrior2);
		cm2.printCharacter1Info();
		
		cm2.setWizard(wizard2);
		cm2.printCharacter2Info();
		
		// cm2.setHunter(hunter2);
		// cm2.printCharacter3Info();
		
		
		
		return "home";
	}
	
}
