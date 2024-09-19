package kr.co.hanbit.productmanagement.infrastructure;

import kr.co.hanbit.productmanagement.domain.Exception.EntityNotFoundException;
import kr.co.hanbit.productmanagement.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
        return null;
    }

    public List<Product> findAll() {
        return null;
    }

    public List<Product> findByName(String name) {
        return null;
    }

    public Product update(Product product) {
        return null;
    }

    public void delete(Long id) {
    }

}
