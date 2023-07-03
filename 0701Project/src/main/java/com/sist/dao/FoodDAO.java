package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.common.CreateDataBase;
import com.sist.vo.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDataBase db=new CreateDataBase();
	private static FoodDAO dao;
	public static FoodDAO newInstance()
	{
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	
	// random page
	public List<FoodVO> randomFoodList()
	{
		List<FoodVO> flist=new ArrayList<FoodVO>();
		try
		{
			conn=db.getConnection();
			// 메인페이지 더보기 거기에 랜덤하게 출력해주기
			String sql="SELECT poster,title,score,price,fdno "
					+ "FROM (SELECT poster,title,score,price,fdno FROM food_detail "
					+ "ORDER BY DBMS_RANDOM.RANDOM)"
					+ "WHERE rownum <=4 ";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				String poster= rs.getString(1);
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				vo.setPoster(poster);
				vo.setTitle(rs.getString(2));
				vo.setScore(rs.getDouble(3));
				vo.setPrice(rs.getString(4));
				vo.setFdno(rs.getInt(5));
				flist.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			db.disConnection(conn, ps);
		}
	
		return flist;
	}
}
