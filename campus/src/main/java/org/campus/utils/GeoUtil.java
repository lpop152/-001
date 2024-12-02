package org.campus.utils;

import org.springframework.data.geo.Point;

import java.util.Arrays;
import java.util.List;

public class GeoUtil {
    /**
     * 射线法
     * （射线法的基本思想是从待判断的点向任意方向（通常是水平方向）发射一条射线，
     * 然后检查这条射线与多边形边界的交点数量。如果交点数量是奇数，则点在多边形内部；
     * 如果交点数量是偶数，则点在多边形外部。）
     *
     * 判断一个点是否在多边形内
     *
     * @param point   要判断的点
     * @return 如果在多边形内返回 true，否则返回 false
     */

    public static boolean isPointInPolygon(Point point) {
        List<Point> polygon = Arrays.asList(
                new Point(110.998446, 34.698851),
                new Point(110.993268, 34.694365),
                new Point(111.003884, 34.686349),
                new Point(111.008234, 34.691304)
        );
        int n = polygon.size();
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            double xi = polygon.get(i).getX(), yi = polygon.get(i).getY();
            double xj = polygon.get(j).getX(), yj = polygon.get(j).getY();

            double intersect = ((yi > point.getY()) != (yj > point.getY()))
                    ? (point.getX() < (xj - xi) * (point.getY() - yi) / (yj - yi) + xi) ? 1.0 : 0.0
                    : 0.0;
            if (intersect == 1.0) inside = !inside;
        }

        return inside;
    }

}
