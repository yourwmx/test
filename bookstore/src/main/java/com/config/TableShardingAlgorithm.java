//package com.config;
//
//
//import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
//import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
//import com.google.common.collect.Range;
//
//import java.util.Collection;
//import java.util.LinkedHashSet;
//
///**
// * 分表策略类
// */
//public class TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<String> {
//
//    @Override
//    public String doEqualSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
//        for (String each : tableNames) {
//            if (each.endsWith("1") && Long.valueOf(shardingValue.getValue())%10000 <= 5000) {
//                return each;
//            }else if(each.endsWith("2") && Long.valueOf(shardingValue.getValue())%10000 > 5000){
//                return each;
//            }
//        }
//        throw new IllegalArgumentException();
//    }
//
//    @Override
//    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(tableNames.size());
//        for (String value : shardingValue.getValues()) {
//            for (String tableName : tableNames) {
//                if (tableName.endsWith("1") && Long.valueOf(shardingValue.getValue())%10000 <= 5000) {
//                    result.add(tableName);
//                }else if (tableName.endsWith("2") && Long.valueOf(shardingValue.getValue())%10000 > 5000) {
//                    result.add(tableName);
//                }
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(tableNames.size());
//        Range<String> range = (Range<String>) shardingValue.getValueRange();
//        for (Long i = Long.valueOf(range.lowerEndpoint()); i <= Long.valueOf(range.upperEndpoint()); i++) {
//            for (String each : tableNames) {
//                if (each.endsWith("1") && Long.valueOf(shardingValue.getValue())%10000 <= 5000) {
//                    result.add(each);
//                }else if(each.endsWith("2") && Long.valueOf(shardingValue.getValue())%10000 > 5000){
//                    result.add(each);
//                }
//            }
//        }
//        return result;
//    }
//}
