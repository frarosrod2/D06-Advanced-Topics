<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous.favourite-link" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.paco" action="https://ev.us.es"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.nico" action="https://github.com/nicpazsar"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.alberto" action="https://www.informatica.us.es/"/>
		</acme:menu-option>

<!-- -------------------------------- ANONYMOUS --------------------------------------- -->
		
		<acme:menu-option code="master.menu.anonymous.bulletin.bulletins" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.bulletin.pazos.create" action="/anonymous/pazos-bulletin/create"/> 
		  	<acme:menu-suboption code="master.menu.anonymous.bulletin.pazos.list" action="/anonymous/pazos-bulletin/list"/>
		  	<acme:menu-separator/>
		  	<acme:menu-suboption code="master.menu.anonymous.bulletin.rosa.create" action="/anonymous/rosa-bulletin/create"/> 
		  	<acme:menu-suboption code="master.menu.anonymous.bulletin.rosa.list" action="/anonymous/rosa-bulletin/list"/>
		
		  	<acme:menu-separator/>
		  	<acme:menu-suboption code="master.menu.anonymous.bulletin.murillo.create" action="/anonymous/murillo-bulletin/create"/> 
		  	<acme:menu-suboption code="master.menu.anonymous.bulletin.murillo.list" action="/anonymous/murillo-bulletin/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.anonymous.technologyRecord" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.technologyRecord.list" action="/anonymous/technology-record/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.anonymous.toolRecord" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.toolRecord.list" action="/anonymous/tool-record/list"/>
		</acme:menu-option>


		<acme:menu-option code="master.menu.anonymous.notice" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.notice.list" action="/anonymous/notice/list"/>
		</acme:menu-option>

<!-- -------------------------------- ADMIN --------------------------------------- -->

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.customisations.display" action="/administrator/customisation/display"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show" />
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.notice.create" action="/administrator/notice/create"/>
			<acme:menu-suboption code="master.menu.administrator.notice.list" action="/administrator/notice/list"/>
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.administrator.createInquiries" action="/administrator/inquiry/create"/>
			<acme:menu-suboption code="master.menu.administrator.listInquiries" action="/administrator/inquiry/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createOvertures" action="/administrator/overture/create"/>
			<acme:menu-suboption code="master.menu.administrator.listOvertures" action="/administrator/overture/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createChallenges" action="/administrator/challenge/create"/>
			<acme:menu-suboption code="master.menu.administrator.listChallenges" action="/administrator/challenge/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createTechnologyRecords" action="/administrator/technology-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.listTechnologyRecords" action="/administrator/technology-record/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.createToolRecords" action="/administrator/tool-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.listToolRecords" action="/administrator/tool-record/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.acceptBookkeeper" action="/administrator/bookkeeper-request/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>
				
		<!-- 		Role Menu 			 -->
				
				
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">		
			<acme:menu-suboption code="master.menu.patron.createBanners" action="/patron/banner/create"/>
			<acme:menu-suboption code="master.menu.patron.listBanners" action="/patron/banner/list-mine"/>
		</acme:menu-option>			
				
		<acme:menu-option code="master.menu.bookkeeper" access="hasRole('Bookkeeper')">
			<acme:menu-suboption code="master.menu.bookkeeper.investmentRound.listWritten" action="/bookkeeper/investment-round/list-written"/>
			<acme:menu-suboption code="master.menu.bookkeeper.investmentRound.list" action="/bookkeeper/investment-round/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<acme:menu-suboption code="master.menu.entrepreneur.investment-round.list_mine" action="/entrepreneur/investment-round/list_mine"/>
			<acme:menu-suboption code="master.menu.entrepreneur.application.list_mine" action="/entrepreneur/application/list_mine"/>
			<acme:menu-suboption code="master.menu.entrepreneur.investment-round.create" action="/entrepreneur/investment-round/create"/>			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.application.list_mine" action="/investor/application/list_mine"/>
			<acme:menu-suboption code="master.menu.investor.investmentRound.list" action="/investor/investment-round/list"/>			
		</acme:menu-option>
		
		<!-- 		Authenticated Menu 			 -->		

		<acme:menu-option code="master.menu.authenticated.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.inquiry.list" action="/authenticated/inquiry/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.overture.list" action="/authenticated/overture/list"/>
			<acme:menu-separator/>					
			<acme:menu-suboption code="master.menu.authenticated.technologyRecord.list" action="/authenticated/technology-record/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.notice.list" action="/authenticated/notice/list"/>
			<acme:menu-separator/>					
			<acme:menu-suboption code="master.menu.authenticated.toolRecord.list" action="/authenticated/tool-record/list"/>
			<acme:menu-separator/>					
			<acme:menu-suboption code="master.menu.authenticated.challenge.list" action="/authenticated/challenge/list"/>
			<acme:menu-separator/>					
			<acme:menu-suboption code="master.menu.authenticated.investmentRound.list" action="/authenticated/investment-round/list"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.authenticated.menu.forums" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.forums.list" action="/authenticated/forum/list"/>
			<acme:menu-suboption code="master.menu.authenticated.forums.listMine" action="/authenticated/forum/list-mine"/>
			<acme:menu-suboption code="master.menu.authenticated.forums.create" action="/authenticated/forum/create"/>
		</acme:menu-option>
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create" access="!hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update" access="hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create" access="!hasRole('Investor')"/>
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update" access="hasRole('Investor')"/>				
			<acme:menu-suboption code="master.menu.user-account.become-bookkeepeer" action="/authenticated/bookkeeper-request/create" access="!hasRole('Bookkeeper')"/>
			<acme:menu-suboption code="master.menu.user-account.bookkeeper" action="/authenticated/bookkeeper/update" access="hasRole('Bookkeeper')"/>
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>				
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

