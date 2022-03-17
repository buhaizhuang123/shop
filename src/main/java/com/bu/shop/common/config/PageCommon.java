package com.bu.shop.common.config;

import lombok.Data;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author haizhuangbu
 * @date 2022/3/7 2:49 下午
 * @mark PageCommon
 */
@Data
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class PageCommon implements Interceptor {

    private static final Integer MAPPED_STATEMENT_INDEX = 0;
    private static final Integer PARAMETER_INDEX = 1;
    private static final Integer ROW_BOUNDS_INDEX = 2;

    private Logger log =  LoggerFactory.getLogger(PageCommon.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("------------------------ mybatis ---------------------------");
        Object[] args = invocation.getArgs();
        RowBounds rb = (RowBounds) args[ROW_BOUNDS_INDEX];
        if (rb == RowBounds.DEFAULT) {
            return invocation.proceed();
        }
        args[ROW_BOUNDS_INDEX] = RowBounds.DEFAULT;

        MappedStatement mappedStatement = (MappedStatement) args[MAPPED_STATEMENT_INDEX];

        BoundSql boundSql = mappedStatement.getBoundSql(args[PARAMETER_INDEX]);

        // 获取sql
        String sql = boundSql.getSql();

        String databaseProductName = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection().getMetaData().getDatabaseProductName();
        System.out.println("databaseId" + databaseProductName);

        if (databaseProductName.equalsIgnoreCase("mysql")) {
            // 拼接sql
            sql = mysql(sql, rb);
        }

        if (databaseProductName.equalsIgnoreCase("oracle")) {
            // 拼接sql
            sql = oracle(sql, rb);
        }


        log.info("执行sql:{}", sql);

        StaticSqlSource sqlSource = new StaticSqlSource(mappedStatement.getConfiguration(), sql, boundSql.getParameterMappings());

        // 通过反射设置MapperStatment 的sqlSource字段
        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(mappedStatement, sqlSource);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }


    private String mysql(String sql, RowBounds rowBounds) {

        String format = String.format("LIMIT %d,%d", rowBounds.getOffset(), rowBounds.getLimit());

        if (sql.contains(format)) {
            sql.replaceAll(format, "");
        }

        sql += format;

        log.info("format:{},sql:{}", format, sql);

        return sql;
    }


    private String oracle(String sql, RowBounds rowBounds) {

        // 为查询语句给到别名
        sql = "select * from (select t1.*, rownum rn from (" + sql +
                ") t1 ) t2 where rn > " + (rowBounds.getOffset() - 1) * rowBounds.getLimit() + " and rn <= " + rowBounds.getOffset() * rowBounds.getLimit();

        log.info("sql:{}", sql);
        return sql;
    }
}
