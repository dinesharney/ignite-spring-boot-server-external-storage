package com.example.ignite.server.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.kubernetes.TcpDiscoveryKubernetesIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("gke-dev")
public class IgniteKubernetesConfig  {
    @Bean
    public Ignite igniteInstance() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        // Configure Kubernetes-based discovery
        TcpDiscoveryKubernetesIpFinder ipFinder = new TcpDiscoveryKubernetesIpFinder();
        ipFinder.setNamespace("ignite-namespace"); // Replace with your namespace
        ipFinder.setServiceName("ignite-service"); // Replace with your service name
        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        discoverySpi.setIpFinder(ipFinder);
        cfg.setDiscoverySpi(discoverySpi);
        // Optional: Set Ignite instance name
        cfg.setIgniteInstanceName("ignite-node");
        // Optional: Enable metrics logging
        cfg.setMetricsLogFrequency(60000);
        // Start Ignite node
        return Ignition.start(cfg);
    }
}
