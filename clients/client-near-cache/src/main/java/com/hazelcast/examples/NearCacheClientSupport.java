package com.hazelcast.examples;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import static com.hazelcast.core.Hazelcast.newHazelcastInstance;

abstract class NearCacheClientSupport extends NearCacheSupport {

    protected static HazelcastInstance initCluster() {
        serverInstance = Hazelcast.newHazelcastInstance();
        return HazelcastClient.newHazelcastClient();
    }

    protected static HazelcastInstance[] initCluster(int clusterSize) {
        HazelcastInstance[] instances = new HazelcastInstance[clusterSize];
        for (int i = 0; i < clusterSize; i++) {
            instances[i] = newHazelcastInstance();
            serverInstance = instances[i];
        }

        for (int i = 0; i < clusterSize; i++) {
            instances[i] = HazelcastClient.newHazelcastClient();
        }
        return instances;
    }
}
