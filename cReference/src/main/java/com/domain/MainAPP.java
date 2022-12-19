package com.domain;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainAPP {

	public static void main(String[] args) {

		// 1. 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cReference");

		
		try {
			//[1] 연관관계를 이용한 데이타 검색
			selectData(emf);
			
			//
			insertData(emf);
		}catch (Exception e) {
			System.out.println("실패 : "+ e.getMessage());
		}finally {
			emf.close();
		}
	}
	
	//[1] 연관관계를 이용한 데이타 검색
	static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		Employee e1 = em.find(Employee.class, 7788);
		System.out.println(e1);
		
		System.out.println(e1.getEname()+"님 정보");
		System.out.println("부서 : "+e1.getDept().getDname());
		
		em.close();
	}
	static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Employee emp1 = new Employee();
		emp1.setEname("홍씨1");
		
		//Department dept = em.find(Department.class, 40);
		Department dept = new Department();
		dept.setDeptno(50);
		dept.setDname("아이티");
		dept.setLoc("인천");
		em.persist(dept);
		
		emp1.setDept(dept);
		
		em.persist(emp1);
		
		tx.commit();
		em.close();
	}
}
