package com.store.config;

import com.couchbase.client.java.env.ClusterEnvironment;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

import java.time.Duration;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Override
    public String getConnectionString() {
        return "couchbases://cb.hpcg93u--hlfxsip.cloud.couchbase.com";
    }

    @Override
    public String getUserName() {
        return "admin";
    }

    @Override
    public String getPassword() {
        return "Couchbase@123";
    }

    @Override
    public String getBucketName() {
        return "e-store";
    }

    @Override
    protected void configureEnvironment(ClusterEnvironment.Builder builder) {
        builder.timeoutConfig().connectTimeout(Duration.ofMillis(15000));
        builder.securityConfig().enableTls(true);
    }

    @Override
    protected String getScopeName() {
        return "e-store";
    }
}
