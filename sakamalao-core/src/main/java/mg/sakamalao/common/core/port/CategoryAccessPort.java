package mg.sakamalao.common.core.port;

import java.util.UUID;

public interface CategoryAccessPort {
    boolean exists(UUID categoryId, UUID projectId);
}
