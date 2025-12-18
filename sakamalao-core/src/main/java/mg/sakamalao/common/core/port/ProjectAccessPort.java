package mg.sakamalao.common.core.port;

import java.util.UUID;

public interface ProjectAccessPort {
    boolean hasAccess(UUID userId, UUID projectId);

    boolean isOwner(UUID userId, UUID projectId);
}
