if arg[2] == "debug" then
    require("lldebugger").start()
  end
  
io.stdout:setvbuf("no")

function love.load()

    --Screen setup

    love.window.setFullscreen(true)
    
    love.window.setVSync(0)

    
    width = love.graphics.getWidth()
    height = love.graphics.getHeight()

    screen = love.graphics.newCanvas()
    mainShader = love.graphics.newShader("main.frag")
    zoom = 1
    depth = 10

    if mainShader:hasUniform("depth") then
        mainShader:send("depth",depth)
    end

    center = {-1.75,0}
end

function love.keypressed(key) 
    if key == "up" then
        depth = depth + 10
        if mainShader:hasUniform("depth") then
            mainShader:send("depth",depth)
        end
    end
    if key == "down" then
        depth = depth - 10
        if mainShader:hasUniform("depth") then
            mainShader:send("depth",depth)
        end
    end
end

function love.wheelmoved(x,y)
    zoom = zoom + zoom * (y / 10)
    if zoom < .1 then
        zoom = .1
    end
end

function love.mousemoved(x,y,dx,dy) 
    if love.mouse.isDown(1) then
        center[1] = center[1] - 4*dx/(zoom*width)
        center[2] = center[2] - 4*dy/(zoom*height)
    end
end

function love.update(dt)
    if (mainShader:hasUniform("zoom")) then
        mainShader:send("zoom",zoom)
    end
    if (mainShader:hasUniform("center")) then
        mainShader:send("center",center)
    end
end

function love.draw()
    love.graphics.setShader(mainShader)
    love.graphics.draw(screen)
    love.graphics.setShader()
    love.graphics.print(zoom,0,0)
end