package com.douzone.guestbook01.dao.test;

import java.util.List;

import com.douzone.guestbook01.dao.Guestbook01Dao;
import com.douzone.guestbook01.vo.Guestbook01Vo;


public class Guestbook01DaoTest {

	public static void main(String[] args) {
			//insertTest();
			//findAllTest();
			//deleteTest();
		Guestbook01Dao dao = new Guestbook01Dao(); 
		List<Guestbook01Vo> list = dao.findAll();
		}

	private static void deleteTest() {
		new Guestbook01Dao().delete(1L, "1234");
		
	}

	private static void findAllTest() {
		List<Guestbook01Vo> list = new Guestbook01Dao().findAll();
		for(Guestbook01Vo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
		Guestbook01Vo vo = new Guestbook01Vo();
		vo.setName("일남");
		vo.setPassword("1234");
		vo.setMsg("그만해 이러다 우리 다죽어");
		new Guestbook01Dao().insert(vo);
		
	}
}
