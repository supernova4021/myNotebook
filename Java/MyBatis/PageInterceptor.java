package com.me.comment.dao.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import com.me.comment.bean.BaseBean;
import com.me.comment.bean.Page;

//指定要拦截的方法
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageInterceptor implements Interceptor{

	public Object intercept(Invocation arg0) throws Throwable {
		StatementHandler statementHandler = (StatementHandler)arg0.getTarget();
		//通过mybatis提供的MetaObject（通过反射实现）来获取statementHandler中的mappedStatement属性
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement"); //boundSql.getAdditionalParameter("delegate.mappedStatement")
		
		String id = mappedStatement.getId(); //获取mapper中的id(select标签)
		if(id.endsWith("ByPage")) {//如果是以ByPage结尾就认为是需要分页的
			BoundSql boundSql = statementHandler.getBoundSql();
			String sql = boundSql.getSql(); //原始sql
			
			//查询总记录数，并将其设置到page对象中
			String countSql = "select count(*) from(" + sql + ")t"; //子查询
			Connection conn = (Connection)arg0.getArgs()[0];
			PreparedStatement statement = conn.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(statement); //通过parameterHandler把sql的参数设置进去(把？替换掉)
			ResultSet rs = statement.executeQuery();
			BaseBean bean = (BaseBean)boundSql.getParameterObject();
			Page page = bean.getPage();
			if(rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}
			
			String pageSql = sql + " limit " + (page.getCurrentPage() - 1) * page.getPageNumber() + "," + page.getPageNumber();
			metaObject.setValue("delegate.boundSql.sql", pageSql);//把改造后的sql修改回去
		}
		return arg0.proceed();
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties arg0) {
		//plugin
	}

}