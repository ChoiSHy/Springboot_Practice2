package kr.co.hanbit.productmanagement.infrastructure;

import kr.co.hanbit.productmanagement.domain.Exception.EntityNotFoundException;
import kr.co.hanbit.productmanagement.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
* JPA를 이용하지 않고, Repository 구현 연습
* SQL 연습과 데이터베이스 연결 연습 목적
*
* */
@Repository
public class DatabaseProductRepository {
    //private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;   // NamedParameterJdbcTemplate 을 이용해 ? 대신 각 속성의 이름으로 입력 가능
    @Autowired
    public DatabaseProductRepository(NamedParameterJdbcTemplate namedJdbcTemplate){
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public Product add(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);
        namedJdbcTemplate
                .update(
                        "insert into products(name, price, amount) values (:name, :price, :amount)",
                        namedParameter, keyHolder);

        Long id = keyHolder.getKey().longValue();
        product.setId(id);
        /*
        JdbcTemplate 사용시
        jdbcTemplate.update("insert into products(name, price, amount) values(?,?,?)",
                product.getName(), product.getPrice(), product.getAmount());
        */
        return product;
    }

    public Product findById(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id",id); // id 매핑을 위해 MapSqlParameterSource 사용
        Product product = namedJdbcTemplate.queryForObject(
                "select id, name, price, amount from products where id = :id",   // select문으로 조건에 맞는 데이터 불러오기
                namedParameter,
                new BeanPropertyRowMapper<>(Product.class)                          // 조회한 정보를 Product 인스턴스로 만들어주기
        );                                                                          // 필요한 조건: 클래스에 인자없는 생성자 + 각 속성에 대한 setter 메서드

        return product;
    }

    public List<Product> findAll() {
        List<Product> products = namedJdbcTemplate.query(
                "select * from products",
                new BeanPropertyRowMapper<>(Product.class)
        );

        return products;
    }

    public List<Product> findByName(String name) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("name", "%"+name+"%");

        List<Product> products = namedJdbcTemplate.query(
                "select * from products where name like :name"
                ,namedParameter
                ,new BeanPropertyRowMapper<>(Product.class)
        );

        return products;
    }

    public Product update(Product product) {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);
        namedJdbcTemplate.update(
                "update products set name=:name, price=:price, amount=:amount where id=:id"
        ,namedParameter);

        return product;
    }

    public void delete(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);

        namedJdbcTemplate.update(
                "delete from products where id=:id",
                namedParameter
        );
    }

}
