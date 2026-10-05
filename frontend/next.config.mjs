/** @type {import('next').NextConfig} */
const nextConfig = {
  /* config options here */
  reactCompiler: true,
  allowedDevOrigins: [`${process.env.ALLOWED_ORIGIN}`],
};

export default nextConfig;
