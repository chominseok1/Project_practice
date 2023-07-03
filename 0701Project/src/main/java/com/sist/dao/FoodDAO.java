package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sist.common.CreateDataBase;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDataBase db=new CreateDataBase();
	private static FoodDAO dao;
}
