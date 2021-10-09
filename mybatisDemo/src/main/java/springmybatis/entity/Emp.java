package springmybatis.entity;

import lombok.Data;

/**
 * 实体类
 *	属性名要与表的字段名一致（大小写可以不一样）
 */
@Data
public class Emp {
	private Integer id;
	private String ename;
	private Integer age;
}



